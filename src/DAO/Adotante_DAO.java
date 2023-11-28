package DAO;
import Fasn.Adotante;

public class Adotante_DAO {
	static String[] campos = {"nome","Email","CPF","Telefone", "Genero", "Endereco_Id","JanelasTeladas"};
	 
	public boolean inserir(Adotante adotante)
		{Conn conn = new Conn();
		 String[] valores = {adotante.getNome(),
				 			 adotante.getEmail(),
				 			 adotante.getCPF(),
				 			 adotante.getTelefone(),
				 			 adotante.getGenero(),
				 			 String.valueOf(adotante.getEndereco().getId()),
				 			 adotante.isJanelasTeladas()?"1":"0"};
		 return conn.insert("Adotante", campos, valores);}

	public boolean alterar(Adotante adotante)
		{Conn conn = new Conn();
		 String[] valores = {adotante.getNome(),
	 			 adotante.getEmail(),
	 			 adotante.getCPF(),
	 			 adotante.getTelefone(),
	 			 adotante.getGenero(),
	 			 String.valueOf(adotante.getEndereco().getId()),
	 			 adotante.isJanelasTeladas()?"1":"0"};
		 return conn.update("Adotante", campos, valores,adotante.getId());}

	public boolean excluir(Adotante adotante)
		{Conn conn = new Conn();
		 return (conn.delete("Adotante",adotante.getId())&&
				 conn.deleteWhere("Adocao", " where Adotante_Id='"+adotante.getId()+"'"));}
	
	
}
