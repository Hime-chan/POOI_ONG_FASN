package Fasn;

public class Adotante extends Pessoa implements Moradia {
	private int id;
	private Endereco endereco;
	private boolean janelasTeladas;

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	public boolean isJanelasTeladas() {return janelasTeladas;}
	public void setJanelasTeladas(boolean janelasTeladas) {this.janelasTeladas = janelasTeladas;}

	@Override
	public String printEndereco() {return endereco.toString();}
}




