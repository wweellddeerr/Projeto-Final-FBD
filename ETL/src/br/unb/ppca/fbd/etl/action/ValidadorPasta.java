package br.unb.ppca.fbd.etl.action;

import java.io.File;

public class ValidadorPasta {
	
	private ValidadorPasta() {}
	
	private static ValidadorPasta instance;
	
	public static ValidadorPasta instance() {
		if(instance == null) {
			instance = new ValidadorPasta();
		}
		
		return instance;
	}

	public void validar(File selectedFile) throws DiretorioInvalidoException {
		throw new DiretorioInvalidoException("A pasta selecionada não contém os arquivos necessários para povoar o banco de dados.");
		
	}

}
