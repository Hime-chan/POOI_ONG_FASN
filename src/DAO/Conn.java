package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conn implements AutoCloseable {
	private static 
		String url = ""
		user = "",
		password = "";
	private Connection conn = null;
	
	public Conn()
		{try {conn = DriverManager.getConnection(url, user, password);} 
	   	 catch (SQLException e) 
		 	{System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());}
		 }
    @Override
    public void close() throws SQLException 
    	{if (conn!= null && !conn.isClosed()) 
    		{conn.close();}}
	
	
	
	public boolean insert(String tabela, String[] campos, String[] valores)
		{if (campos == null || valores == null || campos.length != valores.length) 
			{System.err.println("Erro nos parâmetros de inserção.");
	         return false;}
		
		 StringBuilder sql= new StringBuilder("insert into "+tabela+"(");
		 StringBuilder values= new StringBuilder("(");
		 for (int i = 0; i < campos.length; i++) 
		 	{sql.append(campos[i]+",");
		 	 values.append(valores[i]+",");}
		 sql.deleteCharAt(sql.length() - 1);
		 values.deleteCharAt(values.length() - 1);
		 sql.append(") values "+values+")");
		 try 
			{PreparedStatement ps=conn.prepareStatement(sql.toString());
			 return (ps.executeUpdate()==1);}
		catch (SQLException e) 
		 	{System.err.println("Erro ao inserir: " + e.getMessage());
		 	 System.out.println(sql);
		 	 return false;}
		 }

	public boolean updateWhere(String tabela, String[] campos, String[] valores, String where)
		{if (campos == null || valores == null || campos.length != valores.length) 
			{System.err.println("Erro nos parâmetros de inserção.");
		     return false;}
	
		 StringBuilder sql= new StringBuilder("update "+tabela+" set ");
		 for (int i = 0; i < campos.length; i++) 
		 	{sql.append(campos[i]+" = "+valores[i]+" ,");}
		 sql.deleteCharAt(sql.length() - 1);
		 sql.append(where);
		 try 
			{PreparedStatement ps=conn.prepareStatement(sql.toString());
			 return (ps.executeUpdate()==1);}
		catch (SQLException e) 
		 	{System.err.println("Erro ao alterar (update): " + e.getMessage());
		 	 return false;}
		 }
	
	public boolean update(String tabela, String[] campos, String[] valores, int id)
		{return updateWhere(tabela,campos,valores," where id='"+id+"'");}	
	
	public boolean deleteWhere(String tabela, String where)
		{return updateWhere(tabela, new String[]{"apagado"}, new String[]{"1"}, where);}

	public boolean delete(String tabela, int id)
		{return update(tabela, new String[]{"apagado"}, new String[]{"1"}, id);}

	public int lastInsertId()
		{try (ResultSet resultado = select("select LAST_INSERT_ID() as lastId");)
			{if (resultado.next()) return resultado.getInt("lastId");
			else return 0;}
		 catch (SQLException e) 
		 	{System.err.println("Erro ao encontrar o id inserido: " + e.getMessage());
		 	 return 0;}}
	
	public ResultSet select(String sql)
		{try 
			{Statement statem=conn.createStatement();
			 return statem.executeQuery(sql);}
		 catch (SQLException e) 
		 	{System.err.println("Erro ao fazer o select: " + e.getMessage());
		 	 System.out.println(sql);
		 	 return null;}}

	public ResultSet selectWhere(String tabela, String where, String limitOrder)
		{return select("select * from "+tabela+" where "+where+" and Apagado=0 "+limitOrder);}
	
	
}
