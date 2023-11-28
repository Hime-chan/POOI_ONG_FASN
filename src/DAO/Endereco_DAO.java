package DAO;
import Fasn.Endereco;

public class Endereco_DAO {
	static String[] campos = {"CEP","Cidade","Estado","Logradouro", "Numero", "Bairro","Complemento"};
	public boolean inserir(Endereco endereco)
		{Conn conn = new Conn();
		 String[] valores = {endereco.getCEP(),
				 			 endereco.getCidade(),
				 			 endereco.getEstado(),
				 			 endereco.getLogradouro(),
				 			 String.valueOf(endereco.getNumero()),
				 			 endereco.getBairro(),
				 			 endereco.getComplemento()};
		 return conn.insert("Endereco", campos, valores);}	

	public boolean alterar(Endereco endereco)
		{Conn conn = new Conn();
		 String[] valores = {endereco.getCEP(),
				 			 endereco.getCidade(),
				 			 endereco.getEstado(),
				 			 endereco.getLogradouro(),
				 			 String.valueOf(endereco.getNumero()),
				 			 endereco.getBairro(),
				 			 endereco.getComplemento()};
		 return conn.update("Endereco", campos, valores, endereco.getId());}
	
	public boolean excluir(Endereco endereco)
		{Conn conn = new Conn();
		 return conn.delete("Endereco",endereco.getId());}
}
