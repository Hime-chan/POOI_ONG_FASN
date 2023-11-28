package Fasn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
        String url = "jdbc:mysql://mysql.purrfect.codes:3306/purrfect06";
        String user = "purrfect06";
        String password = "POO1ONGFASN";
        
        try (Connection connection = DriverManager.getConnection(url, user, password)) 
        	{System.out.println("Conexão bem-sucedida!");} 
        	 catch (SQLException e) {System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());}
		}
	}

