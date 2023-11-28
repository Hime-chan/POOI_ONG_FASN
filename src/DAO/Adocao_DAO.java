package DAO;
import java.time.format.DateTimeFormatter;
import Fasn.Adocao;

public class Adocao_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static String[] campos = {"Adotante_Id", "Animal_Id","Data"};
	
	public boolean inserir(Adocao adocao)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(adocao.getAdotante().getId()),
				 String.valueOf(adocao.getAdotado().getId()),
			 	 adocao.getData().format(formatter)};
		 return conn.insert("Adocao", campos, valores);}
	
	public boolean alterar(Adocao adocao)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(adocao.getAdotante().getId()),
				 String.valueOf(adocao.getAdotado().getId()),
			 	 adocao.getData().format(formatter)};
		 return conn.update("Adocao", campos, valores,adocao.getId());}

	public boolean excluir(Adocao adocao)
		{Conn conn = new Conn();
		 return conn.delete("Adocao",adocao.getId());}	
	
}
