package br.unb.ppca.fbd.etl.dominio;

public enum UF {

	AC("Acre", true), 
	AL("Alagoas", true), 
	AM("Amazonas", true),
	AP("Amap�", true),
	BA("Bahia", true),
	CE("Cear�", true),
	DF("Distrito Federal", false),
	ES("Esp�rito Santo", true),
	GO("Goi�s", true),
	MA("Maranh�o", true),
	MG("Minas Gerais", true),
	MS("Mato Grosso do Sul", true),
	MT("Mato Grosso", true),
	PA("Par�", true),
	PB("Para�ba", true),
	PE("Pernambuco", true),
	PI("Piau�", true),
	PR("Paran�", true),
	RJ("Rio de Janeiro", true),
	RN("Rio Grande do Norte", true),
	RO("Rond�nia", true),
	RR("Roraima", true),
	RS("Rio Grande do Sul", true),
	SC("Santa Catarina", true),
	SE("Sergipe", true),
	SP("S�o Paulo", true),
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
