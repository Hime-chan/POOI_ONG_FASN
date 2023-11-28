package DAO;

import java.time.format.DateTimeFormatter;
import Fasn.HorarioTrabalho;

public class HorarioTrabalho_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	static String[] campos = {"veterinario_id", "dia_semana","hora_inicio","hora_fim"};

	public boolean inserir(HorarioTrabalho horario)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(horario.getVeterinario().getId()),
				 			 String.valueOf(horario.getDiaSemana().getValue()),
				 			 horario.getHoraInicio().format(formatter),
				 			 horario.getHoraFim().format(formatter)};
		 return conn.insert("Veterinario_HorariosDeTrabalho", campos, valores);}
	
	public boolean alterar(HorarioTrabalho horario)
	{Conn conn = new Conn();
	 String[] valores = {String.valueOf(horario.getVeterinario().getId()),
			 			 String.valueOf(horario.getDiaSemana().getValue()),
			 			 horario.getHoraInicio().format(formatter),
			 			 horario.getHoraFim().format(formatter)};
	 return conn.update("Veterinario_HorariosDeTrabalho", campos, valores, horario.getId());}

	public boolean excluir(HorarioTrabalho horario)
		{Conn conn = new Conn();
		 return conn.delete("Veterinario_HorariosDeTrabalho",horario.getId());}	
	
	
}
