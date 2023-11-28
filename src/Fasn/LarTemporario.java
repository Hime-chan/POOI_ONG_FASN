package Fasn;

public class LarTemporario implements Moradia{
	private int id;
	private int vagasTotais;
	private int vagasOcupadas;
	private Endereco endereco;

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getVagasTotais() {return vagasTotais;}
	public void setVagasTotais(int vagasTotais) {this.vagasTotais = vagasTotais;}
	public int getVagasOcupadas() {return vagasOcupadas;}
	public void setVagasOcupadas(int vagasOcupadas) {this.vagasOcupadas = vagasOcupadas;}
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	
	@Override
	public String printEndereco() {return endereco.toString();}
	

}
