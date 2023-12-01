package DAO;
import Fasn.Adotante;
import Fasn.Endereco;
import Fasn.HorarioTrabalho;
import Fasn.LarTemporario;
import Fasn.ResponsavelLarTemporario;
import Fasn.Veterinario;
import System.MenuManager;
import System.gerais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Veterinario_DAO {
	static String[] campos = {"Nome", "email","CPF","CRV", "CNPJ","telefone","Genero"};
	private static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);
	private static DateTimeFormatter formatterSoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public static boolean inserir(Veterinario veterinario)
		{String[] valores = {"'"+veterinario.getNome()+"'",
					"'"+veterinario.getEmail()+"'",
					"'"+veterinario.getCPF()+"'",
					"'"+veterinario.getCRV()+"'",
					"'"+veterinario.getCNPJ()+"'",
					"'"+veterinario.getTelefone()+"'",
					"'"+veterinario.getGenero()+"'"};
			 return conn.insert("Veterinario", campos, valores);
		 }
	
	public static boolean alterar(Veterinario veterinario)
	{String[] valores = {"'"+veterinario.getNome()+"'",
			"'"+veterinario.getEmail()+"'",
			"'"+veterinario.getCPF()+"'",
			"'"+veterinario.getCRV()+"'",
			"'"+veterinario.getCNPJ()+"'",
			"'"+veterinario.getTelefone()+"'",
			"'"+veterinario.getGenero()+"'"};
	 return conn.update("Veterinario", campos, valores, veterinario.getId());
		 }
	
	public static boolean excluir(Veterinario veterinario)
		{try(Conn conn = new Conn();)
			{return (conn.delete("Veterinario",veterinario.getId())
					 &&
					 conn.deleteWhere("Veterinario_HorariosDeTrabalho", "where veterinario_id='"+veterinario.getId()+"'"));}
		 catch (SQLException e) 
			{System.err.println("Erro ao obter resultados: " + e.getMessage());
	         return false;}
		 }	

	public static Veterinario loginCRVorCNPJ(int CRVorCNPJ)
		{try (ResultSet resultado = conn.selectWhere("Veterinario", " (CRV = '"+CRVorCNPJ+"' or CNPJ='"+CRVorCNPJ+"') ", " order by Id"))
			 	{if (!resultado.next()) {return null;}
				 return new Veterinario  (resultado.getInt("Id"),
										  resultado.getString("Nome"),
										  resultado.getString("email"),
										  resultado.getString("CPF"),
										  resultado.getString("CRV"),
										  resultado.getString("CNPJ"),
										  resultado.getString("Telefone"),
										  resultado.getString("Genero"));}
			 catch (SQLException e) 
			 	{System.err.println("Erro ao obter resultados: " + e.getMessage());
		         return null;}}
	
	public static Veterinario selecionar()
		{Map<Integer, Veterinario> mapVeterinarios = new HashMap<>();
		 try (ResultSet resultado = conn.selectWhere("Veterinario", " true ", " order by Id"))
			{while (resultado.next()) 
				{mapVeterinarios.put(resultado.getInt("Id"), new Veterinario
											(resultado.getInt("Id"),
											 resultado.getString("Nome"),
											 resultado.getString("email"),
											 resultado.getString("CPF"),
											 resultado.getString("telefone"),
											 resultado.getString("genero"),
											 resultado.getString("CRV"),
											 resultado.getString("CNPJ")));
				String sql_turnos="select * from Veterinario_HorariosDeTrabalho where Apagado=0 and veterinario_id="+resultado.getInt("Id");
				ResultSet turnos_result = conn.select(sql_turnos);
				ArrayList<HorarioTrabalho> turnos = new ArrayList<>();
				while (turnos_result.next())
					{turnos.add(new HorarioTrabalho(turnos_result.getInt("Id"), 
													mapVeterinarios.get(resultado.getInt("Id")),
													DayOfWeek.of(turnos_result.getInt("dia_semana")), 
													LocalTime.parse(turnos_result.getString("hora_inicio"), formatterSoHora), 
													LocalTime.parse(turnos_result.getString("hora_fim"), formatterSoHora)));}
				 mapVeterinarios.get(resultado.getInt("Id")).setHorariosTrabalho(turnos.toArray(new HorarioTrabalho[0]));
				 System.out.println(String.valueOf(resultado.getInt("Id"))+":"
											 +resultado.getString("Nome")
											 +", CRV "+resultado.getString("CRV"));
				 }
			 int selecionado;
			  do {System.out.println("Selecione um dos veterinários para visualizar os dados (digite o Id): ");
			  	  selecionado=scanner.nextInt();}
			while (!mapVeterinarios.containsKey(selecionado)); 
			scanner.nextLine();
			return mapVeterinarios.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}

	public static Veterinario cadastro()
		{System.out.println("Cadastro de Veterinários.");
		//(int id, String nome, String email, String cPF, String telefone, String genero, String cRV, String cNPJ)
		 Veterinario veterinario = new Veterinario
				 						(0, 
				 						 gerais.perguntaString("Digite o nome do veterinário: "), 
				 						 gerais.perguntaString("Digite o e-mail do veterinário: "), 
				 						 gerais.perguntaString("Digite o CPF do veterinário: "), 
				 						 gerais.perguntaString("Digite o telefone do veterinário: "), 
				 						 gerais.perguntaString("Digite o gênero do veterinário: "), 
				 						 gerais.perguntaString("Digite o CRV do veterinário: "), 
				 						 gerais.perguntaString("Digite o CNPJ do veterinário: "));
		 int qtd_horarios = gerais.perguntaInt("Quantos turnos de trabalho este veterinário estará disponível?");
		 HorarioTrabalho[] horarios = new HorarioTrabalho[qtd_horarios];
		 for(int i=0;i<qtd_horarios;i++)
		 	{System.out.println((i+1)+"º turno:");
		 	 horarios[i]=new HorarioTrabalho
		 			 			(0,
		 			 			 veterinario,
		 			 			 gerais.perguntaDiaSemana("Escolha o dia da semana:"),
		 			 			 gerais.perguntaHora("Escolha o horário de início:"),
		 			 			 gerais.perguntaHora("Escolha o horário de fim:"));
			 }
		 veterinario.setHorariosTrabalho(horarios);
		 inserir(veterinario);
		 veterinario.setId(conn.lastInsertId());
		 for(int i=0;i<qtd_horarios;i++)
		 	{HorarioTrabalho_DAO.inserir(horarios[i]);
		 	 horarios[i].setId(conn.lastInsertId());}
		 return veterinario;
		 }		
	
	public static void visualizarAlterar()
		{Veterinario selecionado = selecionar();
		 System.out.println("O veterinário selecionado tem as seguintes características:");
		 selecionado.Detalhes();
		 if (gerais.perguntaBool("Quer alterá-lo?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }	

	
	public static Veterinario alteracao(Veterinario antigo)
		{System.out.println("Alteração de veterinários:");
		 antigo.setNome(gerais.perguntaString("Qual o novo nome do veterinário (O antigo é "+antigo.getNome()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo e-mail do veterinário (O antigo é "+antigo.getEmail()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo CPF do veterinário (O antigo é "+antigo.getCPF()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo telefone do veterinário (O antigo é "+antigo.getTelefone()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo gênero do veterinário (O antigo é "+antigo.getGenero()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo CRV do veterinário (O antigo é "+antigo.getCRV()+")?"));
		 antigo.setNome(gerais.perguntaString("Qual o novo CNPJ do veterinário (O antigo é "+antigo.getCNPJ()+")?"));
		//public HorarioTrabalho(int id, Veterinario veterinario, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim) 
		if (gerais.perguntaBool("Você quer adicionar algum turno de trabalho para esse veterinário?"))
			{HorarioTrabalho novoTurno=new HorarioTrabalho(0, antigo,
													 gerais.perguntaDiaSemana("Escolha o dia da semana:"),
													 gerais.perguntaHora("Escolha o horário de início:"),
													 gerais.perguntaHora("Escolha o horário de fim:"));
			 HorarioTrabalho_DAO.inserir(novoTurno);
			 }
		if (gerais.perguntaBool("Você quer apagar algum turno de trabalho para esse veterinário?"))
			{HorarioTrabalho_DAO.excluir(HorarioTrabalho_DAO.selecionar(antigo.getId()));}
		 return antigo;}	
	
	
	
	
}
