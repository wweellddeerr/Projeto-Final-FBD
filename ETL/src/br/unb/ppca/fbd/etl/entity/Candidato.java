package br.unb.ppca.fbd.etl.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Candidato {
	
	@Id
	@Column
	private BigInteger cpf;
	
	@Column(name="COD_TSE_MUNIC_NASC")
	private Integer codigoMunicipioNascimento;
	
	@Column(name="COD_NACIONALIDADE")
	private Integer codigoNacionalidade;
	
	@Column(name="COD_OCUPACAO")
	private Integer codigoOcupacao;
	
	@Column(name="COD_GRAU_INST")
	private Integer codigoGrauInstrucao;
	
	@Column
	private String nome;
	
	@Column(name="DT_NASCIMENTO")
	private Date dataNascimento;
	
	@Column(name="NRO_TITULO")
	private BigInteger numeroTitulo;
	
	@Column
	private String email;
	
	@Column
	private String sexo;
	
	@Column(name="ESTADO_CIVIL")
	private String estadoCivil;

	public Candidato(BigInteger cpf, Integer codigoMunicipioNascimento, Integer codigoNacionalidade, Integer codigoOcupacao,
			Integer codigoGrauInstrucao, String nome, Date dataNascimento, BigInteger numeroTitulo, String email,
			String sexo, String estadoCivil) {
		this.cpf = cpf;
		this.codigoMunicipioNascimento = codigoMunicipioNascimento;
		this.codigoNacionalidade = codigoNacionalidade;
		this.codigoOcupacao = codigoOcupacao;
		this.codigoGrauInstrucao = codigoGrauInstrucao;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.numeroTitulo = numeroTitulo;
		this.email = email;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
	}

	public BigInteger getCpf() {
		return cpf;
	}

	public Integer getCodigoMunicipioNascimento() {
		return codigoMunicipioNascimento;
	}

	public Integer getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	public Integer getCodigoOcupacao() {
		return codigoOcupacao;
	}

	public Integer getCodigoGrauInstrucao() {
		return codigoGrauInstrucao;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public BigInteger getNumeroTitulo() {
		return numeroTitulo;
	}

	public String getEmail() {
		return email;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Candidato && ((Candidato) obj).getCpf().equals(getCpf());
	}
}
