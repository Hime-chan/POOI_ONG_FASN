package DAO;
import Fasn.Animal;
import Fasn.AtendimentoVeterinario;
import Fasn.Adocao;
import Fasn.Adotante;
import Fasn.Endereco;
import Fasn.Evento;
import Fasn.LarTemporario;
import Fasn.Veterinario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import System.gerais;
import System.MenuManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Animal_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static String[] campos ={"Especie_Id","Nome","DataNascimento","DataNascimentoAproximada", "DataMorte", "LarTemporario_Id"};
	static Conn conn = new Conn();
	
	public static boolean inserir(Animal animal)
		{String[] valores = {String.valueOf((animal.getEspecieId())),
				 			 "'"+animal.getNome()+"'",
				 			"'"+animal.getDataNasc().format(formatter)+"'",
				 			 animal.isDataNascAprox()? "1" : "0",
				 			(animal.getDataMorte()!=null?"'"+animal.getDataMorte().format(formatter)+"'":"null"),
						 	(animal.getLarTemp()!=null?String.valueOf(animal.getLarTemp().getId()):"null")};
		 boolean inserido=conn.insert("Animal", campos, valores);
		 animal.setId(conn.lastInsertId());
		 return inserido;}
	
	public static boolean alterar(Animal animal)
		{String[] valores = {String.valueOf((animal.getEspecieId())),
				 			 "'"+animal.getNome()+"'",
				 			 "'"+animal.getDataNasc().format(formatter)+"'",
				 			 animal.isDataNascAprox()? "1" : "0",
				 					(animal.getDataMorte()!=null?"'"+animal.getDataMorte().format(formatter)+"'":"null"),
						 	 (animal.getLarTemp()!=null?String.valueOf(animal.getLarTemp().getId()):"null")};
		 return conn.update("Animal", campos, valores,animal.getId());}

	public static boolean excluir(Animal animal)
		{return (conn.delete("Animal",animal.getId())&&
				 conn.deleteWhere("Adocao", "where Animal_Id='"+animal.getId()+"'"));}

	public static Animal cadastro()
		{System.out.println("Cadastro de animais.");
		 String nome = gerais.perguntaString("Digite o nome do animal: ");
		 System.out.println("As espécies possíveis são:");
		 for (Map.Entry<Integer, String> entry : Animal.especie_codigo.entrySet()) 
		 	{System.out.println(entry.getKey()+": "+entry.getValue());}
		 int especie_id = gerais.perguntaInt("Digite o identificador da espécie: ");
		 LocalDate dataNasc = gerais.perguntaData("Digite a data de nascimento: ");
		 Boolean dataNascAprox = gerais.perguntaBool("Essa data é aproximada? ");
		 LarTemporario larTemp=null;
		 if (gerais.perguntaBool("Ele está num lar temporário? "))
		 	{larTemp=LarTemporario_DAO.selecionar();}
		 Animal animal = new Animal(0,nome,especie_id,dataNascAprox,dataNasc,null,larTemp); 
		 inserir(animal); 
		 return animal;}

// Animal(int id, String nome, int especie_id, boolean dataNascAprox, 
//	  LocalDate dataNasc, LocalDate dataMorte, LarTemporario larTemp) 	
	
	public static Animal selecionar()
		{Map<Integer, Animal> mapa = new HashMap<>();
		 Scanner scanner = new Scanner(System.in);
		 String sql="select *, LarTemporario.Id as lt_Id, Animal.Id as a_Id, e.Id as e_Id \r\n"
				 		+ "from Animal\r\n"
				 		+ "left join LarTemporario on LarTemporario.Id=Animal.LarTemporario_Id\r\n"
				 		+ "left join Endereco e on e.Id=LarTemporario.Endereco_Id \r\n"
				 		+ "where Animal.apagado=0 \r\n"
				 		+ "order by Animal.Id";
		 try (ResultSet resultado = conn.select(sql))
			{while (resultado.next()) 
				{LarTemporario larTemp = null;
				 if (resultado.getInt("lt_Id")!=0)
				 	{larTemp =new LarTemporario
						(resultado.getInt("lt_Id"),
								 resultado.getInt("VagasTotais"),
								 resultado.getInt("VagasOcupadas"),
								 new Endereco
								 	(resultado.getInt("e_Id"),
								 	 resultado.getString("Logradouro"),
								 	 resultado.getInt("Numero"),
								 	 resultado.getString("Bairro"),
								 	 resultado.getString("CEP"),
								 	 resultado.getString("Complemento"),
								 	 resultado.getString("Cidade"),
								 	 resultado.getString("Estado")));}
				 mapa.put(resultado.getInt("a_Id"), new Animal
						 							(resultado.getInt("a_Id"),
						 							 resultado.getString("Nome"),
						 							 resultado.getInt("especie_id"),
						 							 (resultado.getInt("DataNascimentoAproximada")==1),
						 							 LocalDate.parse(resultado.getString("DataNascimento"), formatter),
						 							 (resultado.getString("DataMorte")!=null?LocalDate.parse(resultado.getString("DataMorte"), formatter):null),
						 							 larTemp));
				 //Consultas veterinárias do animal:
				 String sql_consultas="select *,av.Id as av_Id, Veterinario.Id as v_Id \r\n"
								 		+ " from AtendimentoVeterinario av \r\n"
								 		+ " left join Veterinario on Veterinario.Id=av.Veterinario_Id "
								 		+ " where Animal_Id="+resultado.getInt("a_Id")
								 		+ " and av.Apagado=0";
				 
				 ArrayList<Evento> eventos = new ArrayList<>();
				 ResultSet consultas=conn.select(sql_consultas);
				 while (consultas.next())
				 	{AtendimentoVeterinario consulta=new AtendimentoVeterinario
				 												(consultas.getInt("av_Id"),
				 												 new Veterinario
				 												 	(consultas.getInt("v_Id"),
				 												 	 consultas.getString("Nome"),
				 												 	 consultas.getString("email"),
				 												 	 consultas.getString("CPF"),
				 												 	 consultas.getString("Telefone"),
				 												 	 consultas.getString("Genero"),
				 												 	 consultas.getString("CRV"),
				 												 	 consultas.getString("CNPJ")),
				 												 mapa.get(resultado.getInt("a_Id")),
				 												 LocalDateTime.parse(consultas.getString("DataHorario"), formatterHora),
				 												 consultas.getString("ComentariosVeterinario"));
				 	eventos.add(consulta);}
				 String sql_adocoes="select *, Adocao.Id as Adocao_Id \r\n"
				 		+ " from Adocao \r\n"
				 		+ " left join Adotante on Adotante.Id=Adotante_Id\r\n"
				 		+ " left join Endereco on Adotante.Endereco_Id = Endereco.Id \r\n"
				 		+ " where Animal_Id="+resultado.getInt("a_Id")+" and Adocao.apagado=0";
				 
				 ResultSet adocoes=conn.select(sql_adocoes);
				 while (adocoes.next())
				 	{Adocao adocao=new Adocao
				 						 (adocoes.getInt("Adocao_Id"),
				 						  new Adotante
				 						  		(adocoes.getInt("Adotante_Id"),
				 						  		 adocoes.getString("nome"),
				 						  		 adocoes.getString("Email"),
				 						  		 adocoes.getString("CPF"),
				 						  		 adocoes.getString("Telefone"),
				 						  		 adocoes.getString("Genero"),
				 						  		 new Endereco
				 						  		 	(adocoes.getInt("Endereco_Id"),
				 						  		 	 adocoes.getString("Logradouro"),
				 						  		 	 adocoes.getInt("Numero"),
				 						  		 	 adocoes.getString("Bairro"),
				 						  		 	 adocoes.getString("CEP"),
				 						  		 	 adocoes.getString("Complemento"),
				 						  		 	 adocoes.getString("Cidade"),
				 						  		 	 adocoes.getString("Estado")),
				 						  		 (adocoes.getInt("JanelasTeladas")==1)),
				 						  mapa.get(resultado.getInt("a_Id")),
				 						  LocalDateTime.parse(adocoes.getString("Data"), formatterHora));
				 	 eventos.add(adocao); 
				 	 }
				 mapa.get(resultado.getInt("a_Id")).setEventos(eventos.toArray(new Evento[0]));
				 
				 System.out.println(String.valueOf(resultado.getInt("Id"))+":"+resultado.getString("nome"));
				 }
			 int selecionado;
			  do {System.out.println("Selecione um dos animais (digite o Identificador)");
			  	  selecionado=scanner.nextInt();}
			while (!mapa.containsKey(selecionado));
		  	scanner.nextLine();
			return mapa.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}

	public static Animal alteracao(Animal antigo)
		{System.out.println("Alteração de animais.");
		 String nome = gerais.perguntaString("Digite o novo nome do animal (Nome atual: "+antigo.getNome()+"):");
		 System.out.println("As espécies possíveis são:");
		 for (Map.Entry<Integer, String> entry : Animal.especie_codigo.entrySet()) 
		 	{System.out.println(entry.getKey()+": "+entry.getValue());}
		 int especie_id = gerais.perguntaInt("Digite o novo identificador da espécie : (Espécie atual: "+antigo.getEspecie()+"): ");
		 LocalDate dataNasc = gerais.perguntaData("Digite a data de nascimento  (Data atual: "+antigo.getDataNasc()+"):");
		 Boolean dataNascAprox = gerais.perguntaBool("Essa data é aproximada? (Atualmente "+(antigo.isDataNascAprox()?"":"não ")+"é) ");
		 
		 LocalDate dataMorte=null; 
		 if (gerais.perguntaBool("Ele se foi?"))
		 	{dataMorte = gerais.perguntaData("Digite a data de morte:");}
		 
		 LarTemporario larTemp=null;
		 if (gerais.perguntaBool("Ele está num lar temporário? (Atualmente "+(antigo.getLarTemp()==null?"não está":" está no "+antigo.getLarTemp().getId())+") "))
		 	{larTemp=LarTemporario_DAO.selecionar();}
		 
		 antigo.setNome(nome);
		 antigo.setDataNasc(dataNasc);
		 antigo.setEspecie(especie_id);
		 antigo.setDataNascAprox(dataNascAprox);
		 if (dataMorte!=null) {antigo.setDataMorte(dataMorte);}
		 antigo.setLarTemp(larTemp);
		 alterar(antigo); 
		 return antigo;}

	
	public static void visualizarAlterar()
		{Animal selecionado = selecionar();
		 System.out.println("O animal selecionado tem as seguintes características:");
		 selecionado.Detalhes();
		 if (gerais.perguntaBool("Quer alterá-lo?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }
	
	
}
