package Fasn;

import java.time.LocalDate;

public class Adocao implements Evento {
	private int id;
	private Adotante adotante;
	private Animal adotado;
	private LocalDate data;
	
	public Adotante getAdotante() {return adotante;}
	public void setAdotante(Adotante adotante) {this.adotante = adotante;}
	public Animal getAdotado() {return adotado;}
	public void setAdotado(Animal adotado) {this.adotado = adotado;}
	public LocalDate getData() {return data;}
	public void setData(LocalDate data) {this.data = data;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Override
	public void printLineEvento() {
		// TODO Auto-generated method stub
		
	}

}
