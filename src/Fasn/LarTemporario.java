package Fasn;

public class LarTemporario implements Moradia{
	private int id;
	private int vagasTotais;
	private int vagasOcupadas;
	private Endereco endereco;
	private ResponsavelLarTemporario[] responsaveis;

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getVagasTotais() {return vagasTotais;}
	public void setVagasTotais(int vagasTotais) {this.vagasTotais = vagasTotais;}
	public int getVagasOcupadas() {return vagasOcupadas;}
	public void setVagasOcupadas(int vagasOcupadas) {this.vagasOcupadas = vagasOcupadas;}
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	public ResponsavelLarTemporario[] getResponsaveis() {return this.responsaveis;}
	public void setResponsaveis(ResponsavelLarTemporario[] responsaveis) {this.responsaveis=responsaveis;}
	
	@Override
	public String printEndereco() {return endereco.toString();}
	public LarTemporario(int id, int vagasTotais, int vagasOcupadas, Endereco endereco) 
		{this.id = id;
		 this.vagasTotais = vagasTotais;
		 this.vagasOcupadas = vagasOcupadas;
		 this.endereco = endereco;}
	
	public void Detalhes() 
		{System.out.println("Lar temporário "+getId()+", com "+vagasTotais+" vagas ("+vagasOcupadas+" ocupadas)");
		 System.out.println("Endereço:");
		 System.out.println("\t"+printEndereco());
		 System.out.println("Responsáveis:");
		 for (ResponsavelLarTemporario responsavel : responsaveis) 
			{System.out.println("\t"+responsavel.getNome());}
		 }	
}
