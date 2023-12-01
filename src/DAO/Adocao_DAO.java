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
import Fasn.Endereco;
import Fasn.LarTemporario;
import System.MenuManager;
import System.gerais;

public class Adocao_DAO {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static String[] campos = {"Adotante_Id", "Animal_Id","Data"};
	private static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);
	 
	public static boolean inserir(Adocao adocao)
		{String[] valores = 
			    {String.valueOf(adocao.getAdotante().getId()),
				 String.valueOf(adocao.getAdotado().getId()),
			 	 "'"+adocao.getData().format(formatterHora)+"'"};
		 return conn.insert("Adocao", campos, valores);}

	public static Adocao cadastro()
		{System.out.println("Cadastro de novas adoções.");
		 System.out.println("Selecione o adotante:");
		 Adotante adotante=Adotante_DAO.selecionar();
		 System.out.println("Selecione o animal a ser adotado:");
		 Animal adotado=Animal_DAO.selecionar();
		 Adocao adocao = new Adocao(0,adotante,adotado,gerais.perguntaDataHora("Qual foi a data e hora da adoção? "));
		 inserir(adocao); 
		 adocao.setId(conn.lastInsertId());
		 return adocao;}	
	
	public static boolean alterar(Adocao adocao)
		{String[] valores = {String.valueOf(adocao.getAdotante().getId()),
				 String.valueOf(adocao.getAdotado().getId()),
			 	 "'"+adocao.getData().format(formatterHora)+"'"};
		 return conn.update("Adocao", campos, valores,adocao.getId());}

	public static boolean excluir(Adocao adocao)
		{return conn.delete("Adocao",adocao.getId());}	
	
	public static Adocao selecionar()
		{Map<Integer, Adocao> mapAdocoes = new HashMap<>();
		 String sql = "select Adocao.Id as a_Id,Adocao.Data,"
			       +" Adotante.Id as at_Id, Adotante.nome as at_nome, Adotante.email as at_email, Adotante.CPF as at_CPF, " 
			       +" Adotante.Telefone as at_Telefone, Adotante.Genero as at_Genero, Adotante.JanelasTeladas as at_JanelasTeladas,   \r\n"
			       +" e1.Id as end_at_Id, e1.CEP as end_at_CEP, e1.Cidade as end_at_Cidade, e1.Estado as end_at_Estado, "
			       +" e1.Logradouro as end_at_Logradouro, e1.Numero as end_at_Numero, e1.Bairro as end_at_Bairro, e1.Complemento as end_at_Complemento, \r\n"       
			       +" Animal.Id as an_Id, Animal.Nome as an_Nome, Animal.Especie_Id as an_EspecieId, Animal.DataNascimento as an_DataNascimento, \r\n"       
			       +" Animal.DataNascimentoAproximada as an_DataNascimentoAproximada, Animal.DataMorte as an_DataMorte, "
			       +" LarTemporario.Id as lt_Id, LarTemporario.VagasTotais as lt_VagasTotais, LarTemporario.VagasOcupadas as lt_VagasOcupadas, \r\n"
			       +" e2.Id as end_lt_Id, e2.CEP as end_lt_CEP, e2.Cidade as end_lt_Cidade, e2.Estado as end_lt_Estado, \r\n"
			       +" e2.Logradouro as end_lt_Logradouro, e2.Numero as end_lt_Numero, e2.Bairro as end_lt_Bairro, e2.Complemento as end_lt_Complemento \r\n"
			+" from Adocao \r\n"
			+" left join Adotante on Adotante.Id=Adotante_id "
			+" left join Endereco e1 on Adotante.endereco_id=e1.Id "
			+" left join Animal on Animal.Id=Animal_Id "
			+" left join LarTemporario on LarTemporario_Id = LarTemporario.Id "
			+" left join Endereco e2 on LarTemporario.endereco_id=e2.Id "
			+" where Adocao.apagado=0 ";
		 try (ResultSet resultado = conn.select(sql))
			{while (resultado.next()) 
				{Adotante adotante = new Adotante
											(resultado.getInt("at_Id"),
											 resultado.getString("at_nome"),
											 resultado.getString("at_email"),
											 resultado.getString("at_CPF"),
											 resultado.getString("at_Telefone"),
											 resultado.getString("at_Genero"),
											 new Endereco(
													 resultado.getInt("end_at_Id"),
													 resultado.getString("end_at_Logradouro"),
													 resultado.getInt("end_at_Numero"),
													 resultado.getString("end_at_Bairro"),
													 resultado.getString("end_at_CEP"),
													 resultado.getString("end_at_Complemento"),
													 resultado.getString("end_at_Cidade"),
													 resultado.getString("end_at_Estado")),
											 (resultado.getInt("at_JanelasTeladas")==1));
				 LarTemporario larTemp = new LarTemporario
						 							(resultado.getInt("lt_Id"),
						 							 resultado.getInt("lt_VagasTotais"),
						 							 resultado.getInt("lt_VagasOcupadas"),
													 new Endereco(
															 resultado.getInt("end_at_Id"),
															 resultado.getString("end_lt_Logradouro"),
															 resultado.getInt("end_lt_Numero"),
															 resultado.getString("end_lt_Bairro"),
															 resultado.getString("end_lt_CEP"),
															 resultado.getString("end_lt_Complemento"),
															 resultado.getString("end_lt_Cidade"),
															 resultado.getString("end_lt_Estado")));
				 Animal adotado = new Animal
						 				(resultado.getInt("an_Id"),
				 						 resultado.getString("an_Nome"),
				 						 resultado.getInt("an_EspecieId"),
				 						 (resultado.getInt("an_DataNascimentoAproximada")==1),
				 						 LocalDate.parse(resultado.getString("an_DataNascimento"), formatter),
				 						 (resultado.getString("an_DataMorte")!=null?LocalDate.parse(resultado.getString("an_DataMorte"), formatter):null),
				 						 larTemp);
				 mapAdocoes.put(resultado.getInt("a_Id"), 
						 		new Adocao(resultado.getInt("a_Id"),
						 				   adotante, 
						 				   adotado, 
						 				  LocalDateTime.parse(resultado.getString("Data"), formatterHora)));
				 mapAdocoes.get(resultado.getInt("a_Id")).getAdotante().setId(resultado.getInt("at_Id"));
				 System.out.println(String.valueOf(resultado.getInt("a_Id"))+":"
													 +resultado.getString("an_Nome")
													 +" adotado por "+resultado.getString("at_nome"));
				 }
			 int selecionado;
			  do {System.out.println("Selecione um dos registros de adoção para visualizar os dados (digite o Id)");
			  	  selecionado=scanner.nextInt();}
			while (!mapAdocoes.containsKey(selecionado));
			return mapAdocoes.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}

	public static void visualizarAlterar()
		{Adocao selecionado = selecionar();
		 System.out.println("A adoção selecionada tem as seguintes características:");
		 selecionado.printLineEvento();
		 if (gerais.perguntaBool("Quer alterá-la?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }	


	public static Adocao alteracao(Adocao antigo)
		{System.out.println("Alteração de adoção.");
		 antigo.setData(gerais.perguntaDataHora("Qual foi a data e hora da adoção? ")); 
		 alterar(antigo); 
		 return antigo;}	
	
	
}
