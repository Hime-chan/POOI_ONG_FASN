package Fasn;

public class ResponsavelLarTemporario extends Pessoa {
	private LarTemporario LarTemp;

	public LarTemporario getLarTemp() {return LarTemp;}
	public void setLarTemp(LarTemporario larTemp) {LarTemp = larTemp;}
	public ResponsavelLarTemporario(int id, String nome, String email, String cPF, String telefone, String genero, LarTemporario larTemp) 
		{super(id, nome, email, cPF, telefone, genero);
		 LarTemp = larTemp;}
	
	
}
