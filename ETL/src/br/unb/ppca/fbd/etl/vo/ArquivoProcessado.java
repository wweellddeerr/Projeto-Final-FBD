package br.unb.ppca.fbd.etl.vo;

import java.util.List;

import br.unb.ppca.fbd.etl.entity.Candidato;
import br.unb.ppca.fbd.etl.entity.Candidatura;
import br.unb.ppca.fbd.etl.entity.Eleicao;
import br.unb.ppca.fbd.etl.entity.GrauInstrucao;
import br.unb.ppca.fbd.etl.entity.Municipio;
import br.unb.ppca.fbd.etl.entity.Nacionalidade;
import br.unb.ppca.fbd.etl.entity.Ocupacao;
import br.unb.ppca.fbd.etl.entity.PartidoPolitico;

public class ArquivoProcessado {

	private String nomeArquivo;
	
	private List<Municipio> municipios;
	private List<PartidoPolitico> partidosPoliticos;
	private List<GrauInstrucao> grausInstrucao;
	private List<Ocupacao> ocupacoes;
	private List<Nacionalidade> nacionalidades;
	private List<Eleicao> eleicoes;
	private List<Candidato> candidatos;
	private List<Candidatura> candidaturas;
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
}
