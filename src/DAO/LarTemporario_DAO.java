package DAO;
import Fasn.LarTemporario;

public class LarTemporario_DAO {
	static String[] campos ={"VagasTotais","VagasOcupadas","Endereco_Id"};

	public boolean inserir(LarTemporario lar)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(lar.getVagasTotais()),
				 String.valueOf(lar.getVagasOcupadas()),
				 String.valueOf(lar.getEndereco().getId())};
		 return conn.insert("LarTemporario", campos, valores);}
	
	public boolean alterar(LarTemporario lar)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(lar.getVagasTotais()),
				 String.valueOf(lar.getVagasOcupadas()),
				 String.valueOf(lar.getEndereco().getId())};
		 return conn.update("LarTemporario", campos, valores, lar.getId());}

	public boolean excluir(LarTemporario lar)
		{Conn conn = new Conn();
		 return (conn.delete("LarTemporario",lar.getId())&&
				 conn.deleteWhere("ResponsavelLarTemporario", "where LarTemporario_Id='"+lar.getId()+"'")&&
				 conn.deleteWhere("Animal", "where LarTemporario_Id='"+lar.getId()+"'"));}	

}
