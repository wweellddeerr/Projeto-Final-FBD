package br.unb.ppca.fbd.etl.vo;

import java.math.BigInteger;

public class LinhaProcessada {

	private static final int INDICE_ANO_CANDIDATURA = 2;
	private static final int INDICE_NUMERO_TURNO = 3;
	private static final int INDICE_SIGLA_UF = 5;
	private static final int INDICE_CODIGO_MUNICIPIO = 6;
	private static final int INDICE_NOME_MUNICIPIO = 7;
	private static final int INDICE_CODIGO_CARGO = 8;
	private static final int INDICE_NOME_CARGO = 9;
	private static final int INDICE_NOME_CANDIDATO = 10;
	private static final int INDICE_NUMERO_URNA = 12;
	private static final int INDICE_CPF = 13;
	private static final int INDICE_NOME_URNA = 14;
	private static final int INDICE_CODIGO_SITUACAO_CANDIDATURA = 15;
	private static final int INDICE_DESCRICAO_SITUACAO_CANDIDATURA = 16;
	private static final int INDICE_NUMERO_PARTIDO = 17;
	private static final int INDICE_SIGLA_PARTIDO = 18;
	private static final int INDICE_NOME_PARTIDO = 19;
	private static final int INDICE_CODIGO_OCUPACAO = 24;
	private static final int INDICE_DESCRICAO_OCUPACAO = 25;
	private static final int INDICE_DATA_NASCIMENTO = 26;
	private static final int INDICE_NUMERO_TITULO = 27;
	private static final int INDICE_SEXO = 30;
	private static final int INDICE_CODIGO_GRAU_INSTRUCAO = 31;
	private static final int INDICE_DESCRICAO_GRAU_INSTRUCAO = 32;
	private static final int INDICE_ESTADO_CIVIL = 34;
	private static final int INDICE_CODIGO_NACIONALIDADE = 35;
	private static final int INDICE_DESCRICAO_NACIONALIDADE = 36;
	private static final int INDICE_UF_NASCIMENTO = 37;
	private static final int INDICE_CODIGO_MUNICIPIO_NASCIMENTO = 38;
	private static final int INDICE_NOME_MUNICIPIO_NASCIMENTO = 39;
	private static final int INDICE_CODIGO_SITUACAO = 41;
	private static final int INDICE_EMAIL = 43;
	

	private int anoCandidatura;
	private int numeroTurno;
	private String siglaUF;
	private int codigoMunicipio;
	private String nomeMunicipio;
	private int codigoCargo;
	private String nomeCargo;
	private String nomeCandidato;
	private int numeroUrna;
	private BigInteger cpf;
	private String nomeUrna;
	private int codigoSituacaoCandidatura;
	private String descricaoSituacaoCandidatura;
	private int numeroPartido;
	private String siglaPartido;
	private String nomePartido;
	private int codigoOcupacao;
	private String descricaoOcupacao;
	private String dataNascimento;
	private BigInteger numeroTitulo;
	private String sexo;
	private int codigoGrauInstrucao;
	private String descricaoGrauInstrucao;
	private String estadoCivil;
	private int codigoNacionalidade;
	private String descricaoNacionalidade;
	private String siglaUFNascimento;
	private int codigoMunicipioNascimento;
	private String nomeMunicipioNascimento;
	private int codigoSituacao;
	private String email;
	
	public LinhaProcessada(String[] atributos) {
		anoCandidatura = Integer.parseInt(atributos[INDICE_ANO_CANDIDATURA].replaceAll("\"", ""));
		numeroTurno = Integer.parseInt(atributos[INDICE_NUMERO_TURNO].replaceAll("\"", ""));
		siglaUF = atributos[INDICE_SIGLA_UF].replaceAll("\"", "");
		codigoMunicipio = Integer.parseInt(atributos[INDICE_CODIGO_MUNICIPIO].replaceAll("\"", ""));
		nomeMunicipio = atributos[INDICE_NOME_MUNICIPIO].replaceAll("\"", "");
		codigoCargo = Integer.parseInt(atributos[INDICE_CODIGO_CARGO].replaceAll("\"", ""));
		nomeCargo = atributos[INDICE_NOME_CARGO].replaceAll("\"", "");
		nomeCandidato = atributos[INDICE_NOME_CANDIDATO].replaceAll("\"", "");
		numeroUrna = Integer.parseInt(atributos[INDICE_NUMERO_URNA].replaceAll("\"", ""));
		cpf = new BigInteger(atributos[INDICE_CPF].replaceAll("\"", ""));
		nomeUrna = atributos[INDICE_NOME_URNA].replaceAll("\"", "");
		codigoSituacaoCandidatura = Integer.parseInt(atributos[INDICE_CODIGO_SITUACAO_CANDIDATURA].replaceAll("\"", ""));
		descricaoSituacaoCandidatura = atributos[INDICE_DESCRICAO_SITUACAO_CANDIDATURA].replaceAll("\"", "");
		numeroPartido = Integer.parseInt(atributos[INDICE_NUMERO_PARTIDO].replaceAll("\"", ""));
		siglaPartido = atributos[INDICE_SIGLA_PARTIDO].replaceAll("\"", "");
		nomePartido = atributos[INDICE_NOME_PARTIDO].replaceAll("\"", "");
		codigoOcupacao = Integer.parseInt(atributos[INDICE_CODIGO_OCUPACAO].replaceAll("\"", ""));
		descricaoOcupacao = atributos[INDICE_DESCRICAO_OCUPACAO].replaceAll("\"", "");
		dataNascimento = atributos[INDICE_DATA_NASCIMENTO].replaceAll("\"", "");
		numeroTitulo = new BigInteger(atributos[INDICE_NUMERO_TITULO].replaceAll("\"", ""));
		sexo = atributos[INDICE_SEXO].replaceAll("\"", "");
		codigoGrauInstrucao = Integer.parseInt(atributos[INDICE_CODIGO_GRAU_INSTRUCAO].replaceAll("\"", ""));
		descricaoGrauInstrucao = atributos[INDICE_DESCRICAO_GRAU_INSTRUCAO].replaceAll("\"", "");
		estadoCivil = atributos[INDICE_ESTADO_CIVIL].replaceAll("\"", "");
		codigoNacionalidade = Integer.parseInt(atributos[INDICE_CODIGO_NACIONALIDADE].replaceAll("\"", ""));
		descricaoNacionalidade = atributos[INDICE_DESCRICAO_NACIONALIDADE].replaceAll("\"", "");
		siglaUFNascimento = atributos[INDICE_UF_NASCIMENTO].replaceAll("\"", "");
		codigoMunicipioNascimento = Integer.parseInt(atributos[INDICE_CODIGO_MUNICIPIO_NASCIMENTO].replaceAll("\"", ""));
		nomeMunicipioNascimento = atributos[INDICE_NOME_MUNICIPIO_NASCIMENTO].replaceAll("\"", "");
		codigoSituacao = Integer.parseInt(atributos[INDICE_CODIGO_SITUACAO].replaceAll("\"", ""));
		email = atributos[INDICE_EMAIL].replaceAll("\"", "");
	}

	public int getAnoCandidatura() {
		return anoCandidatura;
	}

	public int getNumeroTurno() {
		return numeroTurno;
	}

	public String getSiglaUF() {
		return siglaUF;
	}

	public int getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public int getCodigoCargo() {
		return codigoCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public String getNomeCandidato() {
		return nomeCandidato;
	}

	public int getNumeroUrna() {
		return numeroUrna;
	}

	public BigInteger getCpf() {
		return cpf;
	}

	public String getNomeUrna() {
		return nomeUrna;
	}

	public int getCodigoSituacaoCandidatura() {
		return codigoSituacaoCandidatura;
	}

	public String getDescricaoSituacaoCandidatura() {
		return descricaoSituacaoCandidatura;
	}

	public int getNumeroPartido() {
		return numeroPartido;
	}

	public String getSiglaPartido() {
		return siglaPartido;
	}

	public String getNomePartido() {
		return nomePartido;
	}

	public int getCodigoOcupacao() {
		return codigoOcupacao;
	}

	public String getDescricaoOcupacao() {
		return descricaoOcupacao;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public BigInteger getNumeroTitulo() {
		return numeroTitulo;
	}

	public String getSexo() {
		return sexo;
	}

	public int getCodigoGrauInstrucao() {
		return codigoGrauInstrucao;
	}

	public String getDescricaoGrauInstrucao() {
		return descricaoGrauInstrucao;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public int getCodigoNacionalidade() {
		return codigoNacionalidade;
	}

	public String getDescricaoNacionalidade() {
		return descricaoNacionalidade;
	}

	public String getSiglaUFNascimento() {
		return siglaUFNascimento;
	}

	public int getCodigoMunicipioNascimento() {
		return codigoMunicipioNascimento;
	}

	public String getNomeMunicipioNascimento() {
		return nomeMunicipioNascimento;
	}

	public int getCodigoSituacao() {
		return codigoSituacao;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "LinhaProcessada [anoCandidatura=" + anoCandidatura + ", numeroTurno=" + numeroTurno + ", siglaUF="
				+ siglaUF + ", codigoMunicipio=" + codigoMunicipio + ", nomeMunicipio=" + nomeMunicipio
				+ ", codigoCargo=" + codigoCargo + ", nomeCargo=" + nomeCargo + ", nomeCandidato=" + nomeCandidato
				+ ", numeroUrna=" + numeroUrna + ", cpf=" + cpf + ", nomeUrna=" + nomeUrna
				+ ", codigoSituacaoCandidatura=" + codigoSituacaoCandidatura + ", descricaoSituacaoCandidatura="
				+ descricaoSituacaoCandidatura + ", numeroPartido=" + numeroPartido + ", siglaPartido=" + siglaPartido
				+ ", nomePartido=" + nomePartido + ", codigoOcupacao=" + codigoOcupacao + ", descricaoOcupacao="
				+ descricaoOcupacao + ", dataNascimento=" + dataNascimento + ", numeroTitulo=" + numeroTitulo
				+ ", sexo=" + sexo + ", codigoGrauInstrucao=" + codigoGrauInstrucao + ", descricaoGrauInstrucao="
				+ descricaoGrauInstrucao + ", estadoCivil=" + estadoCivil + ", codigoNacionalidade="
				+ codigoNacionalidade + ", descricaoNacionalidade=" + descricaoNacionalidade + ", siglaUFNascimento="
				+ siglaUFNascimento + ", codigoMunicipioNascimento=" + codigoMunicipioNascimento
				+ ", nomeMunicipioNascimento=" + nomeMunicipioNascimento + ", codigoSituacao=" + codigoSituacao
				+ ", email=" + email + "]";
	}

}
