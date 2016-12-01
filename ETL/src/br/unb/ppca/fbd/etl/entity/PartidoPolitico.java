package br.unb.ppca.fbd.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTIDO_POLITICO")
public class PartidoPolitico {
	
	public PartidoPolitico(Integer numero, String sigla, String nome) {
		this.numero = numero;
		this.sigla = sigla;
		this.nome = nome;
	}

	@Id
	@Column
	private Integer numero;
	
	@Column
	private String sigla;
	
	@Column
	private String nome;

	public Integer getNumero() {
		return numero;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof PartidoPolitico && ((PartidoPolitico) obj).getNumero().equals(getNumero());
	}
}
