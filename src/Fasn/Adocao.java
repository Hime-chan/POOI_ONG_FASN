package Fasn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adocao implements Evento {
	static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private int id;
	private Adotante adotante;
	private Animal adotado;
	private LocalDateTime data;
	
	public Adotante getAdotante() {return adotante;}
	public void setAdotante(Adotante adotante) {this.adotante = adotante;}
	public Animal getAdotado() {return adotado;}
	public void setAdotado(Animal adotado) {this.adotado = adotado;}
	public LocalDateTime getData() {return data;}
	public void setData(LocalDateTime data) {this.data = data;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	
	
	public Adocao(int id, Adotante adotante, Animal adotado, LocalDateTime data) 
		{this.id = id;
		 this.adotante = adotante;
		 this.adotado = adotado;
		 this.data = data;}

	@Override
	public void printLineEvento() {
		System.out.println(getData().format(formatterHora)+": Adoção");
		System.out.println("\t Adotante:"+getAdotante().getNome());
		System.out.println("\t Adotado:"+getAdotado().getNome());
	}

}
