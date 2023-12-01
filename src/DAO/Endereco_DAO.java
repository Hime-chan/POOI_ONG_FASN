package DAO;
import Fasn.Endereco;
import System.gerais;

public class Endereco_DAO {
	static String[] campos = {"CEP","Cidade","Estado","Logradouro", "Numero", "Bairro","Complemento"};
	static Conn conn = new Conn();
	public static boolean inserir(Endereco endereco)
		{String[] valores = {"'"+endereco.getCEP()+"'",
							 "'"+endereco.getCidade()+"'",
							 "'"+endereco.getEstado()+"'",
							 "'"+endereco.getLogradouro()+"'",
				 			 String.valueOf(endereco.getNumero()),
				 			 "'"+endereco.getBairro()+"'",
				 			 "'"+endereco.getComplemento()+"'"};
		 boolean inserido=conn.insert("Endereco", campos, valores);
		 endereco.setId(conn.lastInsertId());
		 return inserido;}	

	public static boolean alterar(Endereco endereco)
	{String[] valores = {"'"+endereco.getCEP()+"'",
						 "'"+endereco.getCidade()+"'",
						 "'"+endereco.getEstado()+"'",
						 "'"+endereco.getLogradouro()+"'",
						 String.valueOf(endereco.getNumero()),
						 "'"+endereco.getBairro()+"'",
						 "'"+endereco.getComplemento()+"'"};
		 return conn.update("Endereco", campos, valores, endereco.getId());}
	
	public static boolean excluir(Endereco endereco)
		{return conn.delete("Endereco",endereco.getId());}

	public static Endereco alteracao(Endereco antigo)
		{System.out.println("Alteração de Endereço:");
		 antigo.setLogradouro(gerais.perguntaString("Digite o novo logradouro (Logradouro atual: "+antigo.getLogradouro()+"):"));
		 antigo.setNumero(gerais.perguntaInt("Digite o novo número (Número atual: "+antigo.getNumero()+"):"));
		 antigo.setBairro(gerais.perguntaString("Digite o novo Bairro (Bairro atual: "+antigo.getBairro()+"):"));
		 antigo.setComplemento(gerais.perguntaString("Digite o novo Complemento (Complemento atual: "+antigo.getComplemento()+"):"));
		 antigo.setCEP(gerais.perguntaString("Digite o novo CEP (CEP atual: "+antigo.getCEP()+"):"));
		 antigo.setCidade(gerais.perguntaString("Digite a nova Cidade (Cidade atual: "+antigo.getCidade()+"):"));
		 antigo.setEstado(gerais.perguntaString("Digite o novo Estado (Estado atual: "+antigo.getEstado()+"):"));
		 alterar(antigo); 
		 return antigo;}
}
