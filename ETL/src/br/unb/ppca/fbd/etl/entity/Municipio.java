package br.unb.ppca.fbd.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.unb.ppca.fbd.etl.dominio.UF;

@Entity
public class Municipio {
	
	Municipio() {}
	
	public Municipio(Integer codigoTse, String nome, UF uf) {
		this.codigoTSE = codigoTse;
		this.nome = nome;
		this.uf = uf;
	}

	@Id
	@Column(name="COD_TSE")
	private Integer codigoTSE;
	
	@Column
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SIGLA_UF")
	private UF uf;

	public Integer getCodigoTSE() {
		return codigoTSE;
	}

	public String getNome() {
		return nome;
	}

	public UF getUf() {
		return uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTSE == null) ? 0 : codigoTSE.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Municipio && ((Municipio) obj).getCodigoTSE().equals(getCodigoTSE());
	}
}
