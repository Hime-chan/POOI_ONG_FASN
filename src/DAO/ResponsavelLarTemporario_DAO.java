package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Fasn.Endereco;
import Fasn.LarTemporario;
import Fasn.ResponsavelLarTemporario;

public class ResponsavelLarTemporario_DAO {
	static String[] campos = {"nome", "CPF","Telefone","Genero", "LarTemporario_Id","email"};
	private static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);
	
	public static boolean inserir(ResponsavelLarTemporario responsavel)
		{String[] valores = {"'"+responsavel.getNome()+"'",
							 "'"+responsavel.getCPF()+"'",
							 "'"+responsavel.getTelefone()+"'",
							 "'"+responsavel.getGenero()+"'",
				 			 String.valueOf(responsavel.getLarTemp().getId()),
				 			"'"+responsavel.getEmail()+"'"};
		 return conn.insert("ResponsavelLarTemporario", campos, valores);}
	
	public static boolean alterar(ResponsavelLarTemporario responsavel)
		{String[] valores = {"'"+responsavel.getNome()+"'",
				 "'"+responsavel.getCPF()+"'",
				 "'"+responsavel.getTelefone()+"'",
				 "'"+responsavel.getGenero()+"'",
				 String.valueOf(responsavel.getLarTemp().getId()),
				"'"+responsavel.getEmail()+"'"};
		 return conn.update("ResponsavelLarTemporario", campos, valores, responsavel.getId());}

	public static boolean excluir(ResponsavelLarTemporario responsavel)
		{return conn.delete("ResponsavelLarTemporario",responsavel.getId());}	
	public static boolean excluir(int responsavel_id)
		{return conn.delete("ResponsavelLarTemporario",responsavel_id);}	
	
	public static int selecionar(int lt_id)
		{Map<Integer, Integer> mapa = new HashMap<>();
		 String sql="select * from ResponsavelLarTemporario where Apagado=0 and LarTemporario_Id="+lt_id;
		 try (ResultSet resultado = conn.select(sql))
			{while (resultado.next()) 
				{mapa.put(resultado.getInt("Id"), resultado.getInt("Id"));
				 System.out.println(String.valueOf(resultado.getInt("Id"))+": "+resultado.getString("nome"));}
			 int selecionado;
			  do {System.out.println("Selecione um dos lares temporários (digite o Identificador)");
			  	  selecionado=scanner.nextInt();}
			while (!mapa.containsKey(selecionado));
		  	scanner.nextLine();
			return mapa.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return 0;}}
}
