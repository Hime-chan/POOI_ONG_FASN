package DAO;
import Fasn.Veterinario;

public class Veterinario_DAO {
	static String[] campos = {"Nome", "email","CPF","CRV", "CNPJ","telefone","Genero"};
	
	public boolean inserir(Veterinario veterinario)
		{Conn conn = new Conn();
		 String[] valores = {veterinario.getNome(),
				 			 veterinario.getEmail(),
				 			 veterinario.getCPF(),
				 			 veterinario.getCRV(),
				 			 veterinario.getCNPJ(),
				 			 veterinario.getTelefone(),
				 			 veterinario.getGenero()};
		 return conn.insert("Veterinario", campos, valores);}
	
	public boolean alterar(Veterinario veterinario)
		{Conn conn = new Conn();
		 String[] valores = {veterinario.getNome(),
	 			 veterinario.getEmail(),
	 			 veterinario.getCPF(),
	 			 veterinario.getCRV(),
	 			 veterinario.getCNPJ(),
	 			 veterinario.getTelefone(),
	 			 veterinario.getGenero()};
		 return conn.update("Veterinario", campos, valores, veterinario.getId());}
	
	public boolean excluir(Veterinario veterinario)
		{Conn conn = new Conn();
		 return (conn.delete("Veterinario",veterinario.getId())
				 &&
				 conn.deleteWhere("Veterinario_HorariosDeTrabalho", "where veterinario_id='"+veterinario.getId()+"'"));}	
	
}
