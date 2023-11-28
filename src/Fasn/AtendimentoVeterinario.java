package Fasn;

import java.time.LocalDateTime;

public class AtendimentoVeterinario implements Evento {
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



	@Override
	public void printLineEvento() {
		// TODO Auto-generated method stub
		
	}

}
