package Fasn;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class Animal {
	private int id;
	
	private static Map<Integer, String> especie_codigo = new HashMap<>();
	private int especie_id;
	
	private String nome;
	private LocalDate dataNasc,dataMorte;
	private boolean dataNascAprox;
	private LarTemporario larTemp;
	private Evento[] eventos;
	
	
    static {
        // Aqui devo preencher o Map de espécie com dados do banco de dados. 
    	//Exemplo:  especie_codigo.put(0, "Gato"); e etc
    }	
    
    
    public Animal(String nome, int especie_id, boolean dataNascAprox, 
    			  LocalDate dataNasc, LocalDate dataMorte, LarTemporario larTemp) 
    	{this.especie_id = especie_id;
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
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
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
    
	
	
}
