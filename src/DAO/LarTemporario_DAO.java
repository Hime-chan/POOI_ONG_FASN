package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Fasn.Adotante;
import Fasn.Endereco;
import Fasn.LarTemporario;
import Fasn.ResponsavelLarTemporario;
import System.MenuManager;
import System.gerais;

public class LarTemporario_DAO {
	private static String[] campos ={"VagasTotais","VagasOcupadas","Endereco_Id"};
	private static Conn conn = new Conn();
	private static Scanner scanner = new Scanner(System.in);
	
	public static boolean inserir(LarTemporario lar)
		{String[] valores = {String.valueOf(lar.getVagasTotais()),
				 String.valueOf(lar.getVagasOcupadas()),
				 String.valueOf(lar.getEndereco().getId())};
		 return conn.insert("LarTemporario", campos, valores);}
	
	public static boolean alterar(LarTemporario lar)
		{String[] valores = {String.valueOf(lar.getVagasTotais()),
				 String.valueOf(lar.getVagasOcupadas()),
				 String.valueOf(lar.getEndereco().getId())};
		 return conn.update("LarTemporario", campos, valores, lar.getId());}

	public static boolean excluir(LarTemporario lar)
		{return (conn.delete("LarTemporario",lar.getId())&&
				 conn.deleteWhere("ResponsavelLarTemporario", "where LarTemporario_Id='"+lar.getId()+"'")&&
				 conn.deleteWhere("Animal", "where LarTemporario_Id='"+lar.getId()+"'"));}	

	
	public static LarTemporario selecionar()
		{Map<Integer, LarTemporario> mapa = new HashMap<>();
		 String sql="select \r\n"
				 		+ "    lt.*, e.*,\r\n"
				 		+ "    lt.Id AS lt_Id, e.Id AS e_Id\r\n"
				 		+ "    , GROUP_CONCAT(rlt.nome ORDER BY rlt.Id) as responsaveis\r\n"
				 		+ "from LarTemporario lt\r\n"
				 		+ "left join Endereco e on lt.Endereco_Id = e.Id\r\n"
				 		+ "left join ResponsavelLarTemporario rlt on lt.Id = rlt.LarTemporario_Id\r\n"
				 		+ "where lt.Apagado = 0 and rlt.apagado=0 \r\n"
				 		+ "group by lt.Id\r\n"
				 		+ "order by lt_Id\r\n";
		 try (Conn conn = new Conn();  
			  ResultSet resultado = conn.select(sql))
			{while (resultado.next()) 
				{mapa.put(resultado.getInt("lt_Id"), new LarTemporario
											(resultado.getInt("lt_Id"),
											 resultado.getInt("VagasTotais"),
											 resultado.getInt("VagasOcupadas"),
											 new Endereco
											 	(resultado.getInt("e_Id"),
											 	 resultado.getString("Logradouro"),
											 	 resultado.getInt("Numero"),
											 	 resultado.getString("Bairro"),
											 	 resultado.getString("CEP"),
											 	 resultado.getString("Complemento"),
											 	 resultado.getString("Cidade"),
											 	 resultado.getString("Estado"))));
				String sql_responsaveis="select * from ResponsavelLarTemporario where Apagado=0 and LarTemporario_Id="+resultado.getInt("lt_Id");
				ArrayList<ResponsavelLarTemporario> responsaveis= new ArrayList<>();
				ResultSet resultado_responsaveis = conn.select(sql_responsaveis);
				while (resultado_responsaveis.next())
					{responsaveis.add(new ResponsavelLarTemporario
												(resultado_responsaveis.getInt("Id"), 
												 resultado_responsaveis.getString("nome"), 
												 resultado_responsaveis.getString("email"), 
												 resultado_responsaveis.getString("CPF"),
												 resultado_responsaveis.getString("Telefone"),
												 resultado_responsaveis.getString("Genero"), 
												 mapa.get(resultado.getInt("lt_Id"))));}
				mapa.get(resultado.getInt("lt_Id")).setResponsaveis(responsaveis.toArray(new ResponsavelLarTemporario[0]));
				
				System.out.println(String.valueOf(resultado.getInt("Id"))+": Responsáveis: "
											 +resultado.getString("responsaveis")
											 +". Endereço: "+resultado.getString("Logradouro")+", "
											 +String.valueOf(resultado.getInt("Numero"))+".");
				 }
			 int selecionado;
			  do {System.out.println("Selecione um dos lares temporários (digite o Identificador)");
			  	  selecionado=scanner.nextInt();}
			while (!mapa.containsKey(selecionado));
		  	scanner.nextLine();
			return mapa.get(selecionado);
			}
		 catch (SQLException e) 
	 		{System.err.println("Erro ao obter resultados: " + e.getMessage());
	 		 return null;}}

	public static LarTemporario cadastro()
		{System.out.println("Cadastro de Lares temporários.");
		 LarTemporario larTemp = new LarTemporario
										(0, 
										 gerais.perguntaInt("Quantos animais esse lar consegue receber no total? "), 
										 gerais.perguntaInt("Quantos animais esse lar já tem? "), 
										 new Endereco(0,
								 				 gerais.perguntaString("Qual é o logradouro?"),
								 				 gerais.perguntaInt("Qual é o número?"),
								 				 gerais.perguntaString("Qual é o bairro?"),
								 				 gerais.perguntaString("Qual é o CEP?"),
								 				 gerais.perguntaString("Qual é o complemento?"),
								 				 gerais.perguntaString("Qual é a cidade?"),
								 				 gerais.perguntaString("Qual é o estado (coloque a sigla)?")));
		 int qtd_responsaveis = gerais.perguntaInt("Há quantos responsáveis por esse lar temporário?");
		 ResponsavelLarTemporario[] responsaveis = new ResponsavelLarTemporario[qtd_responsaveis];
		 for(int i=0;i<qtd_responsaveis;i++)
		 	{System.out.println((i+1)+"º Responsável:");
		 	 //ResponsavelLarTemporario(int id, String nome, String email, String cPF, String telefone, String genero, LarTemporario larTemp)
		 	 responsaveis[i]=new ResponsavelLarTemporario
		 								(0, 
		 								 gerais.perguntaString("Qual é o nome do "+(i+1)+"º responsável?"), 
		 								 gerais.perguntaString("Qual é o e-mail do "+(i+1)+"º responsável?"), 
		 								 gerais.perguntaString("Qual é o CPF do "+(i+1)+"º responsável?"),
		 								 gerais.perguntaString("Qual é o telefone do "+(i+1)+"º responsável?"),
		 								 gerais.perguntaString("Qual é o gênero do "+(i+1)+"º responsável?"), 
		 								 larTemp);
			 }
		 larTemp.setResponsaveis(responsaveis);
		 inserir(larTemp);
		 larTemp.setId(conn.lastInsertId());
		 for(int i=0;i<qtd_responsaveis;i++)
		 	{ResponsavelLarTemporario_DAO.inserir(responsaveis[i]);
		 	 responsaveis[i].setId(conn.lastInsertId());}
		 return larTemp;
		 }			
	
	
	public static void visualizarAlterar()
		{LarTemporario selecionado = selecionar();
		 System.out.println("O lar temporário selecionado tem as seguintes características:");
		 selecionado.Detalhes();
		 if (gerais.perguntaBool("Quer alterá-lo?"))
			 {alteracao(selecionado);
			  System.out.println("Alteração feita com sucesso.");}
		 MenuManager.menus.get("Vis").ExibirMenu();
		 }	
	
	public static LarTemporario alteracao(LarTemporario antigo)
		{System.out.println("Alteração de lares temporários:");
		if (gerais.perguntaBool("Você quer mudar o endereço desse lar temporário?"))
			{antigo.getEndereco().setLogradouro(gerais.perguntaString("Qual é o novo logradouro?"));
			 antigo.getEndereco().setNumero(gerais.perguntaInt("Qual é o novo número?"));
			 antigo.getEndereco().setBairro(gerais.perguntaString("Qual é o novo bairro?"));
			 antigo.getEndereco().setCEP(gerais.perguntaString("Qual é o novo CEP?"));
			 antigo.getEndereco().setComplemento(gerais.perguntaString("Qual é o novo complemento?"));
			 antigo.getEndereco().setCidade(gerais.perguntaString("Qual é a nova cidade?"));
			 antigo.getEndereco().setEstado(gerais.perguntaString("Qual é o novo estado (coloque a sigla)?"));}
		if (gerais.perguntaBool("Você quer adicionar algum responsável por esse lar temporário?"))
			{ResponsavelLarTemporario novoResponsavel=new ResponsavelLarTemporario
																(0, 
																 gerais.perguntaString("Digite o nome do responsável: "), 
																 gerais.perguntaString("Digite o e-mail do responsável: "), 
																 gerais.perguntaString("Digite o CPF do responsável: "), 
																 gerais.perguntaString("Digite o telefone do responsável: "), 
																 gerais.perguntaString("Digite o gênero do responsável: "), 
																 antigo);
			ResponsavelLarTemporario_DAO.inserir(novoResponsavel);
			 }
		if (gerais.perguntaBool("Você quer apagar algum responsável por esse lar temporário?"))
			{ResponsavelLarTemporario_DAO.excluir(ResponsavelLarTemporario_DAO.selecionar(antigo.getId()));}
		 return antigo;}	
	
	
	
	
	
}
