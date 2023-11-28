package Fasn;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioTrabalho {
	private int id;
	private Veterinario veterinario;
	private DayOfWeek diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public Veterinario getVeterinario() {return veterinario;}
	public void setVeterinario(Veterinario veterinario) {this.veterinario=veterinario;}
	public DayOfWeek getDiaSemana() {return diaSemana;}
	public void setDiaSemana(DayOfWeek diaSemana) {this.diaSemana = diaSemana;}
	public LocalTime getHoraInicio() {return horaInicio;}
	public void setHoraInicio(LocalTime horaInicio) {this.horaInicio = horaInicio;}
	public LocalTime getHoraFim() {return horaFim;}
	public void setHoraFim(LocalTime horaFim) {this.horaFim = horaFim;}
	
}
