package DAO;
import Fasn.ResponsavelLarTemporario;

public class ResponsavelLarTemporario_DAO {
	static String[] campos = {"nome", "CPF","Telefone","Genero", "LarTemporario_Id","email"};

	public boolean inserir(ResponsavelLarTemporario responsavel)
		{Conn conn = new Conn();
		 String[] valores = {responsavel.getNome(),
				 		     responsavel.getCPF(),
				 		     responsavel.getTelefone(),
				 		     responsavel.getGenero(),
				 			 String.valueOf(responsavel.getLarTemp().getId()),
				 			 responsavel.getEmail()};
		 return conn.insert("ResponsavelLarTemporario", campos, valores);}
	
	public boolean alterar(ResponsavelLarTemporario responsavel)
		{Conn conn = new Conn();
		 String[] valores = {responsavel.getNome(),
				 		     responsavel.getCPF(),
				 		     responsavel.getTelefone(),
				 		     responsavel.getGenero(),
				 			 String.valueOf(responsavel.getLarTemp().getId()),
				 			 responsavel.getEmail()};
		 return conn.update("ResponsavelLarTemporario", campos, valores, responsavel.getId());}

	public boolean excluir(ResponsavelLarTemporario responsavel)
		{Conn conn = new Conn();
		 return conn.delete("ResponsavelLarTemporario",responsavel.getId());}	
	
}
