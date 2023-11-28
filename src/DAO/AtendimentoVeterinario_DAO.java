package DAO;
import java.time.format.DateTimeFormatter;
import Fasn.AtendimentoVeterinario;

public class AtendimentoVeterinario_DAO {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static String[] campos ={"Veterinario_Id","Animal_Id","DataHorario","ComentariosVeterinario"};

	public boolean inserir(AtendimentoVeterinario atendimento)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(atendimento.getVeterinario().getId()),
				 String.valueOf(atendimento.getPaciente().getId()),
				 atendimento.getDataHorario().format(formatter),
				 atendimento.getComentariosVeterinario()};
		 return conn.insert("AtendimentoVeterinario", campos, valores);}

	public boolean alterar(AtendimentoVeterinario atendimento)
		{Conn conn = new Conn();
		 String[] valores = {String.valueOf(atendimento.getVeterinario().getId()),
				 String.valueOf(atendimento.getPaciente().getId()),
				 atendimento.getDataHorario().format(formatter),
				 atendimento.getComentariosVeterinario()};
		 return conn.update("AtendimentoVeterinario", campos, valores,atendimento.getId());}

	public boolean excluir(AtendimentoVeterinario atendimento)
		{Conn conn = new Conn();
		 return conn.delete("AtendimentoVeterinario",atendimento.getId());}	
	
}
