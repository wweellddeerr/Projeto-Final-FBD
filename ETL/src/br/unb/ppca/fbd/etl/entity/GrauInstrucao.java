package br.unb.ppca.fbd.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GRAU_INSTRUCAO")
public class GrauInstrucao {

	@Id
	@Column(name="COD")
	private Integer codigo;
	
	@Column
	private String descricao;
	
	public GrauInstrucao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof GrauInstrucao && ((GrauInstrucao) obj).getCodigo().equals(getCodigo());
	}
}
