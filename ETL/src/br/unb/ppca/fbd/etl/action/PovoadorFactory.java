package br.unb.ppca.fbd.etl.action;

import java.io.File;

public class PovoadorFactory {

	private PovoadorFactory() {}
	
	private static PovoadorFactory instance;
	
	static PovoadorFactory instance() {
		if (instance == null) {
			instance = new PovoadorFactory();
		}
		return instance;
	}

	public PovoadorGenerico criarPovoador(File file) {
		if(file.isDirectory()) {
			return new PovoadorPorDiretorio(file);
		}
		return new PovoadorPorArquivo(file);
	}
}
