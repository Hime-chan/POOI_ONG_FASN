package DAO;

import java.util.Map;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import Fasn.Adocao;
import Fasn.Adotante;
import Fasn.Animal;
import Fasn.Endereco;
import Fasn.Evento;
import Fasn.LarTemporario;
import System.MenuManager;
import System.gerais;

public class Adotante_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static String[] campos = {"nome","Email","CPF","Telefone", "Genero", "Endereco_Id","JanelasTeladas"};
	static Conn conn = new Conn();
	
	public static boolean inserir(Adotante adotante)
		{String[] valores = {"'"+adotante.getNome()+"'",
							 "'"+adotante.getEmail()+"'",
							 "'"+adotante.getCPF()+"'",
							 "'"+adotante.getTelefone()+"'",
							 "'"+adotante.getGenero()+"'",
				 			 String.valueOf(adotante.getEndereco().getId()),
				 			 adotante.isJanelasTeladas()?"1":"0"};
		 boolean inserido=conn.insert("Adotante", campos, valores);
		 adotante.setId(conn.lastInsertId());
		 return inserido;}

	public static boolean alterar(Adotante adotante)
	{String[] valores = {"'"+adotante.getNome()+"'",
						 "'"+adotante.getEmail()+"'",
						 "'"+adotante.getCPF()+"'",
						 "'"+adotante.getTelefone()+"'",
						 "'"+adotante.getGenero()+"'",
			 			 String.valueOf(adotante.getEndereco().getId()),
			 			 adotante.isJanelasTeladas()?"1":"0"};
		 return conn.update("Adotante", campos, valores,adotante.getId());}

	public static boolean excluir(Adotante adotante)
		{return (conn.delete("Adotante",adotante.getId())&&
				 conn.deleteWhere("Adocao", " where Adotante_Id='"+adotante.getId()+"'"));}

	public static Adotante cadastro()
		{System.out.println("Cadastro de adotantes.");
		 Adotante adotante = new Adotante(0,
				 			 gerais.perguntaString("Digite o nome do novo pai/mãe: "),
				 			 gerais.perguntaString("Digite o e-mail: "),
				 			 gerais.perguntaString("Digite o CPF: "),
				 			 gerais.perguntaString("Digite o telefone: "),
				 			 gerais.perguntaString("Digite o gênero: "),
				 			 null,
				 			 gerais.perguntaBool("As janelas já estão teladas?"));
		 
		 System.out.println("Agora vamos cadastrar o endereço:");
		 adotante.setEndereco(new Endereco(0,
					 				 gerais.perguntaString("Qual é o logradouro?"),
					 				 gerais.perguntaInt("Qual é o número?"),
					 				 gerais.perguntaString("Qual é o bairro?"),
					 				 gerais.perguntaString("Qual é o CEP?"),
					 				 gerais.perguntaString("Qual é o complemento?"),
					 				 gerais.perguntaString("Qual é a cidade?"),
					 				 gerais.perguntaString("Qual é o estado (coloque a sigla)?")));
		 Endereco_DAO.inserir(adotante.getEndereco());
		 inserir(adotante); 
		 return adotante;
		 }		

	public static Adotante selecionar()
		{Map<Integer, Adotante> mapa = new HashMap<>();
		 Scanner scanner = new Scanner(System.in);
		 String sql="select *,Adotante.Id as adotante_id, Endereco.Id as e_Id  \r\n"
			 		+ "from Adotante \r\n"
			 		+ "left join Endereco on Endereco.Id=Adotante.Endereco_Id\r\n"
			 		+ "where Adotante.apagado=0\r\n"
			 		+ "order by Adotante.Id";
		 try (ResultSet resultado = conn.select(sql))
		 	{while (resultado.next()) 
		 		{mapa.put(resultado.getInt("adotante_id"), 
		 				  new Adotante
		 				  		(resultado.getInt("adotante_id"),
		 				  		 resultado.getString("nome"),	
		 				  		 resultado.getString("Email"),	
		 				  		 resultado.getString("CPF"),	
		 				  		 resultado.getString("Telefone"),
		 				  		 resultado.getString("Genero"),
		 				  		new Endereco(resultado.getInt("e_Id"),
										 	 resultado.getString("Logradouro"),
										 	 resultado.getInt("Numero"),
										 	 resultado.getString("Bairro"),
										 	 resultado.getString("CEP"),
										 	 resultado.getString("Complemento"),
										 	 resultado.getString("Cidade"),
										 	 resultado.getString("Estado")),
		 				  		 (resultado.getInt("JanelasTeladas")==1)));
		 		 String sql_adocoes="select *, \r\n"
					 		 		+ "       LarTemporario.Id as lt_Id, \r\n"
					 		 		+ "       Animal.Id as animal_Id, \r\n"
					 		 		+ "       e.Id as e_Id, \r\n"
					 		 		+ "       Adocao.Id as adocao_Id\r\n"
					 		 		+ "from Animal\r\n"
					 		 		+ "left join LarTemporario on LarTemporario.Id=Animal.LarTemporario_Id\r\n"
					 		 		+ "left join Endereco e on e.Id=LarTemporario.Endereco_Id\r\n"
					 		 		+ "inner join Adocao on (Adocao.Animal_Id=Animal.Id and Adotante_Id="
					 		 		+ resultado.getInt("adotante_id")
					 		 		+" and Adocao.apagado=0 and Animal.apagado=0)\r\n"
					 		 		+ "order by Animal.Id";	
		 		mapa.get(resultado.getInt("adotante_id")).setId(resultado.getInt("adotante_id"));
		 		ArrayList<Evento> adocoes = new ArrayList<>();
		 		ResultSet adocoes_qry=conn.select(sql_adocoes);
		 		while (adocoes_qry.next())
		 			{adocoes.add(new Adocao
		 								(adocoes_qry.getInt("adocao_Id"),
		 								 mapa.get(resultado.getInt("adotante_id")),
		 								 new Animal
		 								 		(adocoes_qry.getInt("adocao_Id"),
		 								 		 adocoes_qry.getString("Nome"),
		 								 		 adocoes_qry.getInt("Especie_Id"),
		 								 		 (adocoes_qry.getInt("DataNascimentoAproximada")==1),
		 								 		 LocalDate.parse(adocoes_qry.getString("DataNascimento"), formatter),
		 								 		(adocoes_qry.getString("DataMorte")!=null?LocalDate.parse(adocoes_qry.getString("DataMorte"), formatter):null),
		 								 		 new LarTemporario(adocoes_qry.getInt("lt_Id"), 
		 								 				 		   adocoes_qry.getInt("VagasTotais"), 
		 								 				 		   adocoes_qry.getInt("VagasOcupadas"), 
			 								 				 		new Endereco
				 													 	(resultado.getInt("e_Id"),
				 													 	 resultado.getString("Logradouro"),
				 													 	 resultado.getInt("Numero"),
				 													 	 resultado.getString("Bairro"),
				 													 	 resultado.getString("CEP"),
				 													 	 resultado.getString("Complemento"),
				 													 	 resultado.getString("Cidade"),
				 													 	 resultado.getString("Estado")))),
		 								 LocalDateTime.parse(adocoes_qry.getString("Data"), formatterHora)));}
		 		mapa.get(resultado.getInt("adotante_id")).setAdocoes(adocoes.toArray(new Adocao[0]));
		 		System.out.println(String.valueOf(resultado.getInt("adotante_id"))+":"+resultado.getString("nome"));}
		    int selecionado;
		    do {System.out.println("Selecione um dos adotantes (digite o Identificador)");
		  	  	selecionado=scanner.nextInt();}
		    while (!mapa.containsKey(selecionado));
		    scanner.nextLine();
		    return mapa.get(selecionado);}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}
	
	
	
	public static void visualizarAlterar()
		{Adotante selecionado = selecionar();
		 System.out.println("O adotante selecionado tem as seguintes características:");
		 selecionado.Detalhes();
		 if (gerais.perguntaBool("Quer alterá-lo?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }

	public static Adotante alteracao(Adotante antigo)
		{System.out.println("Alteração de adotantes:");
		 antigo.setNome(gerais.perguntaString("Digite o novo nome (Nome atual: "+antigo.getNome()+"):"));
		 antigo.setCPF(gerais.perguntaString("Digite o novo CPF (CPF atual: "+antigo.getCPF()+"):"));
		 antigo.setEmail(gerais.perguntaString("Digite o novo E-mail (E-mail atual: "+antigo.getEmail()+"):"));
		 antigo.setTelefone(gerais.perguntaString("Digite o novo Telefone (Telefone atual: "+antigo.getTelefone()+"):"));
		 antigo.setGenero(gerais.perguntaString("Digite o novo Gênero (Gênero atual: "+antigo.getGenero()+"):"));
		 antigo.setJanelasTeladas(gerais.perguntaBool("As janelas estão teladas (atualmente, "+(antigo.isJanelasTeladas()?"":"não ")+"estão)? "));
		 if (gerais.perguntaBool("Quer mudar o endereço (Atualmente, em "+antigo.getEndereco().getLogradouro()+","+antigo.getEndereco().getNumero()+") ?"))
		 	{Endereco_DAO.alteracao(antigo.getEndereco());}
		 alterar(antigo); 
		 return antigo;}	
	
	
	
}
