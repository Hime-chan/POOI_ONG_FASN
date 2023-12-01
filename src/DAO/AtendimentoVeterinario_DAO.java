package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Fasn.Adocao;
import Fasn.Adotante;
import Fasn.Animal;
import Fasn.AtendimentoVeterinario;
import Fasn.Endereco;
import Fasn.LarTemporario;
import Fasn.Veterinario;
import System.MenuManager;
import System.gerais;

public class AtendimentoVeterinario_DAO {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static String[] campos ={"Veterinario_Id","Animal_Id","DataHorario","ComentariosVeterinario"};
	private static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);

	public static boolean inserir(AtendimentoVeterinario atendimento)
		{String[] valores = {String.valueOf(atendimento.getVeterinario().getId()),
				 String.valueOf(atendimento.getPaciente().getId()),
				 "'"+atendimento.getDataHorario().format(formatter)+"'",
				 "'"+atendimento.getComentariosVeterinario()+"'"};
		 return conn.insert("AtendimentoVeterinario", campos, valores);}

	public static boolean alterar(AtendimentoVeterinario atendimento)
		{String[] valores = {String.valueOf(atendimento.getVeterinario().getId()),
				 String.valueOf(atendimento.getPaciente().getId()),
				 "'"+atendimento.getDataHorario().format(formatter)+"'",
				 "'"+atendimento.getComentariosVeterinario()+"'"};
		 return conn.update("AtendimentoVeterinario", campos, valores,atendimento.getId());}

	public static boolean excluir(AtendimentoVeterinario atendimento)
		{return conn.delete("AtendimentoVeterinario",atendimento.getId());}	

	public static AtendimentoVeterinario cadastro()
		{System.out.println("Cadastro de atendimentos veterinários.");
		 System.out.println("Selecione o paciente:");
		 Animal paciente=Animal_DAO.selecionar();
		 System.out.println("Selecione o veterinário responsável:");
		 Veterinario veterinario=Veterinario_DAO.selecionar();
		 AtendimentoVeterinario consulta=new AtendimentoVeterinario
				 						(0, 
				 						veterinario, 
				 						paciente, 
				 						gerais.perguntaDataHora("Qual foi a data e hora do atendimento? "), 
				 						gerais.perguntaString("Digite os comentários relativos ao procedimento."));
		 
		 inserir(consulta);
		 consulta.setId(conn.lastInsertId());
		 return consulta;}		
	
	
	
	public static AtendimentoVeterinario selecionar()
	{Map<Integer, AtendimentoVeterinario> mapa = new HashMap<>();
	 String sql = "select AtendimentoVeterinario.Id as AtendimentoVeterinario_Id, AtendimentoVeterinario.DataHorario, AtendimentoVeterinario.ComentariosVeterinario,\r\n"
	 		+ "Veterinario.Id as at_Id, Veterinario.nome as at_nome, Veterinario.email as at_email, Veterinario.CPF as at_CPF, \r\n"
	 		+ "Veterinario.CNPJ as at_CNPJ, Veterinario.CRV as at_CRV, \r\n"
	 		+ "Veterinario.Telefone as at_Telefone, Veterinario.Genero as at_Genero,\r\n"
	 		+ "Animal.Id as an_Id, Animal.Nome as an_Nome, Animal.Especie_Id as an_EspecieId, Animal.DataNascimento as an_DataNascimento, \r\n"
	 		+ "Animal.DataNascimentoAproximada as an_DataNascimentoAproximada, Animal.DataMorte as an_DataMorte,        \r\n"
	 		+ "LarTemporario.Id as lt_Id, LarTemporario.VagasTotais as lt_VagasTotais, LarTemporario.VagasOcupadas as lt_VagasOcupadas,\r\n"
	 		+ "e2.Id as end_lt_Id, e2.CEP as end_lt_CEP, e2.Cidade as end_lt_Cidade, e2.Estado as end_lt_Estado, \r\n"
	 		+ "e2.Logradouro as end_lt_Logradouro, e2.Numero as end_lt_Numero, e2.Bairro as end_lt_Bairro, e2.Complemento as end_lt_Complemento\r\n"
	 		+ "from AtendimentoVeterinario \r\n"
	 		+ "left join Veterinario on Veterinario.Id=Veterinario_id\r\n"
	 		+ "left join Animal on Animal.Id=Animal_Id\r\n"
	 		+ "left join LarTemporario on LarTemporario_Id = LarTemporario.Id\r\n"
	 		+ "left join Endereco e2 on LarTemporario.endereco_id=e2.Id\r\n"
	 		+ "where AtendimentoVeterinario.apagado=0";
	 try (ResultSet resultado = conn.select(sql))
		{while (resultado.next()) 
			{Veterinario veterinario = new Veterinario
										(resultado.getInt("at_Id"),
										 resultado.getString("at_nome"),
										 resultado.getString("at_email"),
										 resultado.getString("at_CPF"),
										 resultado.getString("at_Telefone"),
										 resultado.getString("at_Genero"),
										 resultado.getString("at_CRV"),
										 resultado.getString("at_CNPJ"));
			 LarTemporario larTemp = new LarTemporario
					 							(resultado.getInt("lt_Id"),
					 							 resultado.getInt("lt_VagasTotais"),
					 							 resultado.getInt("lt_VagasOcupadas"),
												 new Endereco(
														 resultado.getInt("end_lt_Id"),
														 resultado.getString("end_lt_Logradouro"),
														 resultado.getInt("end_lt_Numero"),
														 resultado.getString("end_lt_Bairro"),
														 resultado.getString("end_lt_CEP"),
														 resultado.getString("end_lt_Complemento"),
														 resultado.getString("end_lt_Cidade"),
														 resultado.getString("end_lt_Estado")));
			 Animal paciente = new Animal
					 				(resultado.getInt("an_Id"),
			 						 resultado.getString("an_Nome"),
			 						 resultado.getInt("an_EspecieId"),
			 						 (resultado.getInt("an_DataNascimentoAproximada")==1),
			 						 LocalDate.parse(resultado.getString("an_DataNascimento"), formatter),
			 						 (resultado.getString("an_DataMorte")!=null?LocalDate.parse(resultado.getString("an_DataMorte"), formatter):null),
			 						 larTemp);
			 //AtendimentoVeterinario(int id, Veterinario veterinario, Animal paciente, LocalDateTime dataHorario,
				//String comentariosVeterinario)
			 mapa.put(resultado.getInt("AtendimentoVeterinario_Id"), 
					 		new AtendimentoVeterinario
					 				  (resultado.getInt("AtendimentoVeterinario_Id"),
					 				   veterinario, 
					 				   paciente, 
					 				   LocalDateTime.parse(resultado.getString("DataHorario"), formatterHora),
					 				   resultado.getString("ComentariosVeterinario")));
			 mapa.get(resultado.getInt("AtendimentoVeterinario_Id")).getVeterinario().setId(resultado.getInt("at_Id"));
			 System.out.println(String.valueOf(resultado.getInt("AtendimentoVeterinario_Id"))+":"
												 +resultado.getString("DataHorario")
												 +": Veterinario "+resultado.getString("at_nome")
												 +", Paciente: " +resultado.getString("an_Nome"));
			 }
		 int selecionado;
		  do {System.out.println("Selecione um dos registros de adoção para visualizar os dados (digite o Id)");
		  	  selecionado=scanner.nextInt();}
		while (!mapa.containsKey(selecionado));
		return mapa.get(selecionado);
		}
	 catch (SQLException e) 
 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
 		 return null;}}

	
	
	
	
	
	
	

	public static void visualizarAlterar()
		{AtendimentoVeterinario selecionado = selecionar();
		 System.out.println("O atendimento veterinário teve as seguintes características:");
		 selecionado.printLineEvento();
		 if (gerais.perguntaBool("Quer alterá-lo?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }	


	public static AtendimentoVeterinario alteracao(AtendimentoVeterinario antigo)
		{System.out.println("Alteração de atendimento veterinário.");
		 antigo.setDataHorario(gerais.perguntaDataHora("Qual foi a data e hora do atendimento? "));
		 if (gerais.perguntaBool("Deseja modificar os comentários do veterinário?"))
			 {antigo.setComentariosVeterinario(gerais.perguntaString("Escreva os comentários do veterinário "));} 
		 alterar(antigo); 
		 return antigo;}		
}
