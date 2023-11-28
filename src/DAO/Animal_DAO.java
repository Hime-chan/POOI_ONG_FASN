package DAO;
import Fasn.Animal;
import java.time.format.DateTimeFormatter;

public class Animal_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static String[] campos ={"Especie_Id","Nome","DataNascimento","DataNascimentoAproximada", "DataMorte", "LarTemporario_Id"};
	public boolean inserir(Animal animal)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf((animal.getEspecieId())),
				 			 animal.getNome(),
				 			 animal.getDataNasc().format(formatter),
				 			 animal.isDataNascAprox()? "1" : "0",
						 	 animal.getDataMorte().format(formatter),
						 	String.valueOf(animal.getLarTemp().getId())};
		 return conn.insert("Animal", campos, valores);}
	
	public boolean alterar(Animal animal)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf((animal.getEspecieId())),
				 			 animal.getNome(),
				 			 animal.getDataNasc().format(formatter),
				 			 animal.isDataNascAprox()? "1" : "0",
						 	 animal.getDataMorte().format(formatter),
						 	String.valueOf(animal.getLarTemp().getId())};
		 return conn.update("Animal", campos, valores,animal.getId());}

	public boolean excluir(Animal animal)
		{Conn conn = new Conn();
		 //if(animal.getLarTemp()!=null) 
		 //	{animal.getLarTemp().setVagasOcupadas(animal.getLarTemp().getVagasOcupadas()-1);}
		 return (conn.delete("Animal",animal.getId())&&
				 conn.deleteWhere("Adocao", "where Animal_Id='"+animal.getId()+"'"));}
	
}
