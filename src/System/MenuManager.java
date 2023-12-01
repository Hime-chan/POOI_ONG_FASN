package System;

import java.util.HashMap;
import DAO.Animal_DAO;
import DAO.AtendimentoVeterinario_DAO;
import DAO.Adocao_DAO;
import DAO.Adotante_DAO;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import DAO.Conn;
import DAO.LarTemporario_DAO;
import DAO.Veterinario_DAO;

public class MenuManager {
	public static Map<String, Menu> menus = new HashMap<>();
	
	
	static {	menus.put("Cad", 
	   			 new Menu("Cadastros:",
	   			 new String[] 
	   					 {"Adoção",
	   					  "Adotantes (ou candidatos)",
	   					  "Animal",
	   					  "Atendimento Veterinário",
	   					  "Lar temporário",
	   					  "Veterinário",
	   					  "Voltar ao menu principal"
	   					  },
	   			 new Consumer[] 
	   					 {(parametros -> {if (Adocao_DAO.cadastro()!=null) 
							  					{System.out.println("Cadastro realizado com sucesso.");}
								  		  menus.get("Cad").ExibirMenu();}),
	   					  (parametros -> {if (Adotante_DAO.cadastro()!=null) 
						  						{System.out.println("Cadastro realizado com sucesso.");}
						  				  menus.get("Cad").ExibirMenu();}),
	   					  (parametros -> {if (Animal_DAO.cadastro()!=null) 
	   					  					{System.out.println("Cadastro realizado com sucesso.");}
	   					  				  menus.get("Cad").ExibirMenu();}),
	   					(parametros -> {if (AtendimentoVeterinario_DAO.cadastro()!=null) 
						  					{System.out.println("Cadastro realizado com sucesso.");}
						  				  menus.get("Cad").ExibirMenu();}),
	   					  (parametros -> {if (LarTemporario_DAO.cadastro()!=null) 
					  						{System.out.println("Cadastro realizado com sucesso.");}
					   					  menus.get("Cad").ExibirMenu();}),
	   					  (parametros -> {if (Veterinario_DAO.cadastro()!=null) 
					  						{System.out.println("Cadastro realizado com sucesso.");}
					   					 menus.get("Cad").ExibirMenu();}),
	   					  (parametros -> menus.get("principal").ExibirMenu())}));
	   	
	   	menus.put("Vis", 
	  			 new Menu("Visualizações e alterações:",
	  			 new String[] 
	  					 {"Adoções",
	  					  "Adotantes (ou candidatos)",
	  					  "Animais",
	  					  "Atendimentos veterinários",
	  					  "Lares temporários",
	  					  "Veterinários",
	   					  "Voltar ao menu principal"},
	  			 new Consumer[] 
	  					 {(parametros -> Adocao_DAO.visualizarAlterar()),
		   				  (parametros -> Adotante_DAO.visualizarAlterar()),
	   					  (parametros -> Animal_DAO.visualizarAlterar()),
	   					  (parametros -> AtendimentoVeterinario_DAO.visualizarAlterar()),
	   					  (parametros -> LarTemporario_DAO.visualizarAlterar()),
	   					  (parametros -> Veterinario_DAO.visualizarAlterar()),
	   					  (parametros -> menus.get("principal").ExibirMenu())}));
	
	   	menus.put("Exc", 
	  			 new Menu("Exclusões:",
	  			 new String[] 
	  					 {"Adoções",
	  					  "Adotantes (ou candidatos)",
	  					  "Animais",
	  					  "Atendimentos veterinários",
	  					  "Lares temporários",
	  					  "Veterinários",
	   					  "Voltar ao menu principal"},
	  			 new Consumer[] 
	  					 {(parametros -> {Adocao_DAO.excluir(Adocao_DAO.selecionar());
	  					 				  menus.get("Exc").ExibirMenu();}),
	  					  (parametros -> {Adotante_DAO.excluir(Adotante_DAO.selecionar());
			 				  			  menus.get("Exc").ExibirMenu();}),
	   					  (parametros -> {Animal_DAO.excluir(Animal_DAO.selecionar());
			 				  			  menus.get("Exc").ExibirMenu();}),
	   					  (parametros -> {AtendimentoVeterinario_DAO.excluir(AtendimentoVeterinario_DAO.selecionar());
			 				  			  menus.get("Exc").ExibirMenu();}),
	   					  (parametros -> {LarTemporario_DAO.excluir(LarTemporario_DAO.selecionar());
			 				  			  menus.get("Exc").ExibirMenu();}),
	   					  (parametros -> {Veterinario_DAO.excluir(Veterinario_DAO.selecionar());
			 				  			  menus.get("Exc").ExibirMenu();}),
	   					  (parametros -> menus.get("principal").ExibirMenu())}));
	//Não está sendo usado por enquanto:
	   	menus.put("Vet", 
	  			 new Menu("Sistema para veterinários",
	  			 new String[] 
	  					 {"Atendimentos Veterinários", 
	  					  "Voltar ao menu principal"},
	  			 new Consumer[] 
	  					 {(parametros -> AtendimentoVeterinario_DAO.visualizarAlterar()),
	   					  (parametros -> menus.get("principal").ExibirMenu())}));
	   	
	   	
	       menus.put("principal",
	       		  new Menu("Bem-vindo ao sistema FASN",
	                 new String[] {"Novos Cadastros",
	                             "Visualizações ou alterações",
	                             "Exclusões",
	                             "Fechar o programa"},
	                 new Consumer[] 
	               		{(parametros -> menus.get("Cad").ExibirMenu()),
	               		 (parametros -> menus.get("Vis").ExibirMenu()),
	               		 (parametros -> menus.get("Exc").ExibirMenu()),
	                     (parametros -> System.exit(0))})
	       		  );
		
		}

}
