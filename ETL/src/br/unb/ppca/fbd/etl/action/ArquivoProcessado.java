package br.unb.ppca.fbd.etl.action;

import java.util.List;

public class ArquivoProcessado {

	private String nomeArquivo;
	private List<LinhaProcessada> linhasProcessadas;
	
	public ArquivoProcessado(String nomeArquivo, List<LinhaProcessada> linhasProcessadas) {
		this.nomeArquivo = nomeArquivo;
		this.linhasProcessadas = linhasProcessadas;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public List<LinhaProcessada> getLinhasProcessadas() {
		return linhasProcessadas;
	}
	
}
