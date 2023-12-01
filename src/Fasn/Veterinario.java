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

	private ResponsavelLarTemporario[] responsaveis;
	public Veterinario(int id, String nome, String email, String cPF, String telefone, String genero, String cRV, String cNPJ) 
		{super(id, nome, email, cPF, telefone, genero);
		 CRV = cRV;
		 CNPJ = cNPJ;}
	
	public void Detalhes()
		{System.out.println("Nome: "+getNome());
		 System.out.println("E-mail: "+getEmail());
		 System.out.println("CPF: "+getCPF());
		 System.out.println("Telefone: "+getTelefone());
		 System.out.println("Gênero: "+getGenero());
		 System.out.println("CRV: "+getCRV());
		 System.out.println("CNPJ: "+getCNPJ());
		 if (horariosTrabalho!=null) 
		 	{System.out.println("Turnos de trabalho:");
		 	 for (HorarioTrabalho turno : horariosTrabalho) 
	 			{turno.Detalhes();}}
		 }

	
}
