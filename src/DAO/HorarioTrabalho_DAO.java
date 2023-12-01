package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import Fasn.HorarioTrabalho;

public class HorarioTrabalho_DAO {
	private static DateTimeFormatter formatterDiaSemana = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());
	private static DateTimeFormatter formatterSoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
	static String[] campos = {"veterinario_id", "dia_semana","hora_inicio","hora_fim"};
	static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);

	public static boolean inserir(HorarioTrabalho horario)
		{String[] valores = {String.valueOf(horario.getVeterinario().getId()),
				 			 String.valueOf(horario.getDiaSemana().getValue()),
				 			 "'"+horario.getHoraInicio().format(formatterSoHora)+"'",
				 			 "'"+horario.getHoraFim().format(formatterSoHora)+"'"};
		 return conn.insert("Veterinario_HorariosDeTrabalho", campos, valores);}
	
	public static boolean alterar(HorarioTrabalho horario)
	{String[] valores = {String.valueOf(horario.getVeterinario().getId()),
			 			 String.valueOf(horario.getDiaSemana().getValue()),
			 			 "'"+horario.getHoraInicio().format(formatterSoHora)+"'",
			 			 "'"+horario.getHoraFim().format(formatterSoHora)+"'"};
	 return conn.update("Veterinario_HorariosDeTrabalho", campos, valores, horario.getId());}

	public static boolean excluir(HorarioTrabalho horario)
		{return conn.delete("Veterinario_HorariosDeTrabalho",horario.getId());}	
	
	public static boolean excluir(int horarioId)
		{return conn.delete("Veterinario_HorariosDeTrabalho",horarioId);}	

	public static HorarioTrabalho selecionar(int vet_id)
		{Map<Integer, HorarioTrabalho> mapa = new HashMap<>();
		 String sql="select * from Veterinario_HorariosDeTrabalho where Apagado=0 and veterinario_id="+vet_id;
		 try (ResultSet resultado = conn.select(sql))
			{while (resultado.next()) 
				{mapa.put(resultado.getInt("Id"), new HorarioTrabalho
														(resultado.getInt("Id"), 
														 null, 
														 DayOfWeek.of(resultado.getInt("dia_semana")), 
														 LocalTime.parse(resultado.getString("hora_inicio"), formatterSoHora), 
														 LocalTime.parse(resultado.getString("hora_fim"), formatterSoHora)));
				 System.out.println(String.valueOf(resultado.getInt("Id"))+": ");
				 mapa.get(resultado.getInt("Id")).Detalhes();}
			 int selecionado;
			  do {System.out.println("Selecione um dos turnos (digite o Identificador)");
			  	  selecionado=scanner.nextInt();}
			while (!mapa.containsKey(selecionado));
		  	scanner.nextLine();
			return mapa.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}	
	
}
