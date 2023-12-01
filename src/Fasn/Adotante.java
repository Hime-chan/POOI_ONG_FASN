package Fasn;

public class Adotante extends Pessoa implements Moradia {
	private int id;
	private Endereco endereco;
	private boolean janelasTeladas;
	private Adocao[] adocoes;

	public int getId() {return id; }
	public void setId(int id) {this.id = id;}
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	public boolean isJanelasTeladas() {return janelasTeladas;}
	public void setJanelasTeladas(boolean janelasTeladas) {this.janelasTeladas = janelasTeladas;}
	public Adocao[] getAdocoes() {return this.adocoes;}
	public void setAdocoes(Adocao[] adocoes) {this.adocoes=adocoes;}

	@Override
	public String printEndereco() {return endereco.toString();}
	public Adotante(int id, String nome, String email, String cPF, String telefone, String genero, 
			Endereco endereco, boolean janelasTeladas) 
		{super(id, nome, email, cPF, telefone, genero);
		 this.endereco = endereco;
		 this.janelasTeladas = janelasTeladas;}

	public void Detalhes()
		{System.out.println("Nome: "+getNome());
		 System.out.println("E-mail: "+getEmail());
		 System.out.println("CPF: "+getCPF());
		 System.out.println("Telefone: "+getTelefone());
		 System.out.println("Gênero: "+getGenero());
		 System.out.println("Janelas Teladas: "+(isJanelasTeladas()?"Sim":"Ainda não"));
		 System.out.println("Endereço:");
		 System.out.println(getEndereco().toString());
		 System.out.println("Animais já adotados:");
		 for (Adocao adocaoAtual : adocoes) 
	 		{adocaoAtual.printLineEvento();}
		 }
}




