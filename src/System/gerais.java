package System;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class gerais {
    private static final Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static DateTimeFormatter formatterSoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    
	public static int perguntaInt(String str)
		{String userInput;
		 do {System.out.print(str);
		 	 userInput = scanner.nextLine();}
		 while (!userInput.matches("-?\\d+"));
		 return Integer.parseInt(userInput);}
	
	public static boolean perguntaBool(String str)
		{System.out.print(str+" (Digite 1 para sim e 0 para não):");
		 return scanner.nextLine().equals("1");}
	public static String perguntaString(String str)
		{System.out.print(str);
         return scanner.nextLine();}
	
	public static LocalDate perguntaData(String str)
		{String userInput;
		 do {System.out.print(str+" (no formato yyyy-mm-dd): ");
			 userInput = scanner.nextLine();}
		 while (!userInput.matches("\\d{4}-\\d{2}-\\d{2}"));	
	     return LocalDate.parse(userInput, formatter);}

	public static LocalDateTime perguntaDataHora(String str)
		{String userInput; 
		 do {System.out.print(str+" (no formato yyyy-mm-dd HH:mm:ss): ");
			 userInput = scanner.nextLine();}
		 while (!userInput.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));	
	     return LocalDateTime.parse(userInput, formatterHora);}

	public static LocalTime perguntaHora(String str)
		{String userInput;
		 do {System.out.print(str+" (no formato HH:mm:ss): ");
			 userInput = scanner.nextLine();}
		 while (!userInput.matches("\\d{2}:\\d{2}:\\d{2}"));	
	     return LocalTime.parse(userInput, formatterSoHora);}

	public static DayOfWeek perguntaDiaSemana(String str)
		{int userInput;
		 do {System.out.print(str+"  (Como um número 1-Domingo a 7-Sábado): ");
		 	 userInput = scanner.nextInt();scanner.nextLine();}
		 while ((1>userInput)||(userInput>7));
		 return DayOfWeek.of(userInput);}
}



