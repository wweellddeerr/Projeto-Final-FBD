package br.unb.ppca.fbd.etl.action;

import java.io.File;

public class PovoadorBancoDados {
	
	private PovoadorBancoDados(){}
	
	private static PovoadorBancoDados instance;
	
	public static PovoadorBancoDados instance() {
		if(instance == null) {
			instance = new PovoadorBancoDados();
		}
		return instance;
	}

	public void povoar(File diretorio) throws DiretorioInvalidoException {
		ValidadorPasta.instance().validar(diretorio);
		// TODO Auto-generated method stub
		
	}

}
