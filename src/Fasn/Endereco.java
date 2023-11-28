package Fasn;

public class Endereco {
	private String logradouro;
	private int Id;
	private int numero;
	private String bairro;
	private String CEP;
	private String complemento;
	private String cidade;
	private String estado;
	
	public String getLogradouro() {return logradouro;}
	public void setLogradouro(String logradouro) {this.logradouro = logradouro;}
	public int getId() {return Id;}
	public void setId(int id) {Id = id;}
	public int getNumero() {return numero;}
	public void setNumero(int numero) {this.numero = numero;}
	public String getBairro() {return bairro;}
	public void setBairro(String bairro) {this.bairro = bairro;}
	public String getCEP() {return CEP;}
	public void setCEP(String CEP) {this.CEP = CEP;}
	public String getComplemento() {return complemento;}
	public void setComplemento(String complemento) {this.complemento = complemento;}
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}
	@Override
	public String toString() {
		return logradouro+", n° "+numero+
				", "+complemento+
				". Bairro " + bairro+", CEP "+CEP+
				". Cidade " + cidade + " - " + estado;
	}
	
	
	
}
