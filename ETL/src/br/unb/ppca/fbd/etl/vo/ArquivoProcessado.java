package br.unb.ppca.fbd.etl.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArquivoProcessado {

	private String nomeArquivo;
	private List<LinhaProcessada> linhasProcessadas;
	
	public ArquivoProcessado(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.linhasProcessadas = new ArrayList<LinhaProcessada>();
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public List<LinhaProcessada> getLinhasProcessadas() {
		return Collections.unmodifiableList(linhasProcessadas);
	}
	public void addLinha(LinhaProcessada linhaProcessada) {
		linhasProcessadas.add(linhaProcessada);
	}
}
