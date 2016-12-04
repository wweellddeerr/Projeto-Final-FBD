package br.unb.ppca.fbd.etl.dominio;

public enum UF {
	/**
	 * 
	insert into UF(SIGLA, NOME) VALUES ('AC', 'Acre');
	insert into UF(SIGLA, NOME) VALUES ('AL', 'Alagoas');
	insert into UF(SIGLA, NOME) VALUES ('AM', 'Amazonas');
	insert into UF(SIGLA, NOME) VALUES ('AP', 'Amapá');
	insert into UF(SIGLA, NOME) VALUES ('BA', 'Bahia');
	insert into UF(SIGLA, NOME) VALUES ('CE', 'Ceará');
	insert into UF(SIGLA, NOME) VALUES ('DF', 'Distrito Federal');
	insert into UF(SIGLA, NOME) VALUES ('ES', 'Espírito Santo');
	insert into UF(SIGLA, NOME) VALUES ('GO', 'Goiás');
	insert into UF(SIGLA, NOME) VALUES ('MA', 'Maranhão');
	insert into UF(SIGLA, NOME) VALUES ('MG', 'Minas Gerais');
	insert into UF(SIGLA, NOME) VALUES ('MS', 'Mato Grosso do Sul');
	insert into UF(SIGLA, NOME) VALUES ('MT', 'Mato Grosso');
	insert into UF(SIGLA, NOME) VALUES ('PA', 'Pará');
	insert into UF(SIGLA, NOME) VALUES ('PB', 'Paraíba');
	insert into UF(SIGLA, NOME) VALUES ('PE', 'Pernambuco');
	insert into UF(SIGLA, NOME) VALUES ('PI', 'Piauí');
	insert into UF(SIGLA, NOME) VALUES ('PR', 'Paraná');
	insert into UF(SIGLA, NOME) VALUES ('RJ', 'Rio de Janeiro');
	insert into UF(SIGLA, NOME) VALUES ('RN', 'Rio Grande do Norte');
	insert into UF(SIGLA, NOME) VALUES ('RO', 'Rondônia');
	insert into UF(SIGLA, NOME) VALUES ('RR', 'Roraima');
	insert into UF(SIGLA, NOME) VALUES ('RS', 'Rio Grande do Sul');
	insert into UF(SIGLA, NOME) VALUES ('SC', 'Santa Catarina');
	insert into UF(SIGLA, NOME) VALUES ('SE', 'Sergipe');
	insert into UF(SIGLA, NOME) VALUES ('SP', 'São Paulo');
	insert into UF(SIGLA, NOME) VALUES ('TO', 'Tocantins');
	insert into UF(SIGLA, NOME) VALUES ('ZZ', 'Exterior');
	 * 
	 * 
	 */

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
