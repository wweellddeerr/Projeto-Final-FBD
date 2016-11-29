package br.unb.ppca.fbd.etl.action;

public enum UF {

	AC("Acre"), 
	AL("Alagoas"), 
	AM("Amazonas"),
	AP("Amapá"),
	BA("Bahia"),
	CE("Ceará"),
	ES("Espírito Santo"),
	GO("Goiás"),
	MA("Maranhão"),
	MG("Minas Gerais"),
	MS("Mato Grosso do Sul"),
	MT("Mato Grosso"),
	PA("Pará"),
	PB("Paraíba"),
	PE("Pernambuco"),
	PI("Piauí"),
	PR("Paraná"),
	RJ("Rio de Janeiro"),
	RN("Rio Grande do Norte"),
	RO("Rondônia"),
	RR("Roraima"),
	RS("Rio Grande do Sul"),
	SC("Santa Catarina"),
	SE("Sergipe"),
	SP("São Paulo"),
	TO("Tocantins");
	
	private UF(String nome) {
		this.nome = nome;
	}
	
	private String nome;
	
	public String getNome() {
		return nome;
	}
}
