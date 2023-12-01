package Fasn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AtendimentoVeterinario implements Evento {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int id;
	private Veterinario veterinario;
	private Animal paciente;
	private LocalDateTime dataHorario;
	private String comentariosVeterinario;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public Veterinario getVeterinario() {return veterinario;}
	public void setVeterinario(Veterinario veterinario) {this.veterinario = veterinario;}
	public Animal getPaciente() {return paciente;}
	public void setPaciente(Animal paciente) {this.paciente = paciente;}
	public LocalDateTime getDataHorario() {return dataHorario;}
	public void setDataHorario(LocalDateTime dataHorario) {this.dataHorario = dataHorario;}
	public String getComentariosVeterinario() {return comentariosVeterinario;}
	public void setComentariosVeterinario(String comentariosVeterinario) {this.comentariosVeterinario = comentariosVeterinario;}

	public AtendimentoVeterinario(int id, Veterinario veterinario, Animal paciente, LocalDateTime dataHorario,
			String comentariosVeterinario) 
		{this.id = id;
		 this.veterinario = veterinario;
		 this.paciente = paciente;
		 this.dataHorario = dataHorario;
		 this.comentariosVeterinario = comentariosVeterinario;}
	
	@Override
	public void printLineEvento() {
		System.out.println(getDataHorario().format(formatter)+": Atendimento Veterinário");
		System.out.println("\t Veterinário:"+getVeterinario().getNome());
		System.out.println("\t Paciente:"+getPaciente().getNome());
		System.out.println("\t "+getComentariosVeterinario());
	}

}
