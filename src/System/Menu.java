package System;

import java.util.Scanner;
import java.util.function.Consumer;

public class Menu {
	private String titulo;
	private MenuOpcao[] opcoes;
	
	public Menu(String titulo, String[] opcoes_descs, Consumer<Object[]>[] opcoes_acoes) 
		{this.titulo=titulo;
		 this.opcoes = new MenuOpcao[opcoes_descs.length];
		 for (int i = 0; i < opcoes_descs.length; i++)
		 	{this.opcoes[i]=new MenuOpcao(opcoes_descs[i],opcoes_acoes[i]);}
		 }
	
	public void ExibirMenu()
		{System.out.println("=^..^=   "+titulo+"   =^..^=");
		for (int i = 0; i < opcoes.length; i++)
			{System.out.println((i+1)+":"+opcoes[i].getDescricao());}
		 System.out.println("=^..^=    =^..^=    =^..^=    =^..^=");
		 Scanner scanner = new Scanner(System.in);
		 int escolha;
		 do {System.out.print("Digite sua opção: ");
		 	 escolha=scanner.nextInt()-1;
		 	 scanner.nextLine();}
		 while ((escolha<0) || (opcoes.length<escolha));
		 opcoes[escolha].run(null);
		 }
	
	
	
}
