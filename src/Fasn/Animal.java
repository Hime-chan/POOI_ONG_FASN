package Fasn;

import DAO.Conn;

import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Animal {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int Id;
	
	public static Map<Integer, String> especie_codigo = new HashMap<>();
	private int especie_id;
	
	private String nome;
	private LocalDate dataNasc,dataMorte;
	private boolean dataNascAprox;
	private LarTemporario larTemp;
	private Evento[] eventos;
	
	
    static {
    	try (Conn conn=new Conn();)
    		{ResultSet resultado = conn.select("select * from Espécie order by Id");
    		 while (resultado.next())
    		 	{especie_codigo.put(resultado.getInt("Id"), resultado.getString("nome"));}}
    	catch (SQLException e) 
			{System.err.println("Erro ao obter resultados: " + e.getMessage());}
    }	
    
    
    public Animal(int id, String nome, int especie_id, boolean dataNascAprox, 
    			  LocalDate dataNasc, LocalDate dataMorte, LarTemporario larTemp) 
    	{this.Id=id;
    	 this.nome=nome;
    	 this.especie_id = especie_id;
    	 this.dataNascAprox = dataNascAprox;
    	 this.dataNasc = dataNasc;
    	 this.dataMorte = dataMorte;
    	 this.larTemp = larTemp;}    
    
//Getters e setters    
    public String getEspecie() {return especie_codigo.get(especie_id);}
    public int getEspecieId() {return especie_id;}
    public void setEspecie(int especie_id) {this.especie_id=especie_id;}
    public void setEspecie(String especie) 
    	{for (Map.Entry<Integer, String> id : especie_codigo.entrySet()) 
    	 	{if (id.getValue().equalsIgnoreCase(especie)) 
    	 		{this.especie_id = id.getKey();
                 break;}}}
	public int getId() {return Id;}
	public void setId(int id) {this.Id = id;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public LocalDate getDataNasc() {return dataNasc;}
	public void setDataNasc(LocalDate dataNasc) {this.dataNasc = dataNasc;}
	public LocalDate getDataMorte() {return dataMorte;}
	public void setDataMorte(LocalDate dataMorte) {this.dataMorte = dataMorte;}
	public boolean isDataNascAprox() {return dataNascAprox;}
	public void setDataNascAprox(boolean dataNascAprox) {this.dataNascAprox = dataNascAprox;}
	public LarTemporario getLarTemp() {return larTemp;}
	public void setLarTemp(LarTemporario larTemp) {this.larTemp = larTemp;}
	public Evento[] getEventos() {return eventos;}
	public void setEventos(Evento[] eventos) {this.eventos = eventos;}

	public void Detalhes() 
		{System.out.println("Nome: "+getNome());
		 System.out.println("Espécie: "+getEspecie());
		 System.out.println("Data de Nascimento: "+getDataNasc().format(formatter)+(dataNascAprox?" (aproximada)":""));
		 if (larTemp!=null) {System.out.println("Lar Temporário:"+getLarTemp().getEndereco().getLogradouro()+", "+getLarTemp().getEndereco().getNumero());}
		 System.out.println("Eventos:");
		 for (Evento eventoAtual : eventos) 
		 	{eventoAtual.printLineEvento();}
		 if (getDataMorte()!=null) 
			 System.out.println("✟ "+getNome()+" se tornou uma estrelinha no dia"+getDataMorte().format(formatter));
		 }    

//toString:
	
	
	
}
