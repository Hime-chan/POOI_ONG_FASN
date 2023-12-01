package Fasn;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HorarioTrabalho {
	private int id;
	private Veterinario veterinario;
	private DayOfWeek diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private static DateTimeFormatter formatterDiaSemana = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());
	private static DateTimeFormatter formatterSoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
	

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
	public HorarioTrabalho(int id, Veterinario veterinario, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim) 
		{this.id = id;
		 this.veterinario = veterinario;
		 this.diaSemana = diaSemana;
		 this.horaInicio = horaInicio;
		 this.horaFim = horaFim;}
	
	public void Detalhes()
		{System.out.println(diaSemana.getDisplayName(TextStyle.FULL, Locale.getDefault())+
				": de "+horaInicio.format(formatterSoHora)+" a "+horaInicio.format(formatterSoHora));}
	
	
	
}
