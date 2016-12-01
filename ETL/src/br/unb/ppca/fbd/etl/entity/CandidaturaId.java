package br.unb.ppca.fbd.etl.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CandidaturaId implements Serializable {
	
	private static final long serialVersionUID = -846990843818318039L;

	CandidaturaId(){}
	
	public CandidaturaId(Integer anoCandidatura, BigInteger cpf) {
		this.anoCandidatura = anoCandidatura;
		this.cpf = cpf;
	}
	
	@Column(name="ANO_ELEICAO")
	private Integer anoCandidatura;
	
	@Column(name="CPF_CANDIDATO")
	private BigInteger cpf;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoCandidatura == null) ? 0 : anoCandidatura.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidaturaId other = (CandidaturaId) obj;
		if (anoCandidatura == null) {
			if (other.anoCandidatura != null)
				return false;
		} else if (!anoCandidatura.equals(other.anoCandidatura))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
}
