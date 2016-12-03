package br.unb.ppca.fbd.etl.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Candidatura {
	
	@EmbeddedId
	private CandidaturaId id;

	@Column(name="NUMERO_PARTIDO")
	private Integer numeroPartido;
	
	@Column(name="COD_TSE_MUNIC_ELEICAO")
	private Integer codigoMunicipio;
	
	@Column(name="NUMERO_URNA")
	private Integer numeroUrna;
	
	@Column(name="NOME_URNA")
	private String nomeUrna;
	
	@Column(name="SITUACAO")
	private String descricaoSituacaoCandidatura;
	
	@Column(name="CARGO")
	private String nomeCargo;
	
	@Column(name="SITUACAO_ELEICAO")
	private String situacaoEleicao;
	
	@Column(name="SE_SEGUNDO_TURNO")
	private boolean segundoTurno;
	
	public Candidatura(Integer anoCandidatura, Long cpf, Integer numeroPartido, Integer codigoMunicipio, Integer numeroUrna,
			String nomeUrna, String descricaoSituacaoCandidatura, String nomeCargo, String situacaoEleicao, boolean segundoTurno) {
		this.id = new CandidaturaId(anoCandidatura, cpf);
		this.numeroPartido = numeroPartido;
		this.codigoMunicipio = codigoMunicipio;
		this.numeroUrna = numeroUrna;
		this.nomeUrna = nomeUrna;
		this.descricaoSituacaoCandidatura = descricaoSituacaoCandidatura;
		this.nomeCargo = nomeCargo;
		this.situacaoEleicao = situacaoEleicao;
		this.segundoTurno = segundoTurno;
	}

	public CandidaturaId getId() {
		return id;
	}

	public Integer getNumeroPartido() {
		return numeroPartido;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public Integer getNumeroUrna() {
		return numeroUrna;
	}

	public String getNomeUrna() {
		return nomeUrna;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public String getSituacaoEleicao() {
		return situacaoEleicao;
	}

	public boolean isSegundoTurno() {
		return segundoTurno;
	}

	public String getDescricaoSituacaoCandidatura() {
		return descricaoSituacaoCandidatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Candidatura && ((Candidatura) obj).getId().equals(getId());
	}

}
