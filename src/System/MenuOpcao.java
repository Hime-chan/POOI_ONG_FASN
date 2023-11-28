package System;
import java.util.function.Consumer;

public class MenuOpcao {
	private String descricao;
    private Consumer<Object[]> acao;
    
    public MenuOpcao(String descricao, Consumer<Object[]> acao)
    	{this.descricao=descricao;
    	 this.acao=acao;}
    
    public String getDescricao() {return descricao;}
    public void run(Object... parametros) {acao.accept(parametros);}
    

}
