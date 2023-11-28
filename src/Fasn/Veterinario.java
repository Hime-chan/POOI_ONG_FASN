package Fasn;

public class Veterinario extends Pessoa {
	private String CRV;
	private String CNPJ;
	private HorarioTrabalho[] horariosTrabalho;
	public String getCRV() {return CRV;}
	public void setCRV(String cRV) {CRV = cRV;}
	public String getCNPJ() {return CNPJ;}
	public void setCNPJ(String cNPJ) {CNPJ = cNPJ;}
	public HorarioTrabalho[] getHorariosTrabalho() {return horariosTrabalho;}
	public void setHorariosTrabalho(HorarioTrabalho[] horariosTrabalho) {this.horariosTrabalho = horariosTrabalho;}
	
	
	
}
