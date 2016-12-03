package br.unb.ppca.fbd.etl.dominio;

public enum UF {

	AC("Acre", true), 
	AL("Alagoas", true), 
	AM("Amazonas", true),
	AP("Amapá", true),
	BA("Bahia", true),
	CE("Ceará", true),
	DF("Distrito Federal", false),
	ES("Espírito Santo", true),
	GO("Goiás", true),
	MA("Maranhão", true),
	MG("Minas Gerais", true),
	MS("Mato Grosso do Sul", true),
	MT("Mato Grosso", true),
	PA("Pará", true),
	PB("Paraíba", true),
	PE("Pernambuco", true),
	PI("Piauí", true),
	PR("Paraná", true),
	RJ("Rio de Janeiro", true),
	RN("Rio Grande do Norte", true),
	RO("Rondônia", true),
	RR("Roraima", true),
	RS("Rio Grande do Sul", true),
	SC("Santa Catarina", true),
	SE("Sergipe", true),
	SP("São Paulo", true),
	TO("Tocantins", true),
	ZZ("Exterior", false);
	
	private UF(String nome, boolean temEleicaoMunicipal) {
		this.nome = nome;
		this.temEleicaoMunicipal = temEleicaoMunicipal;
	}
	
	private String nome;
	private boolean temEleicaoMunicipal;
	
	public String getNome() {
		return nome;
	}
	public boolean isTemEleicaoMunicipal() {
		return temEleicaoMunicipal;
	}
}
