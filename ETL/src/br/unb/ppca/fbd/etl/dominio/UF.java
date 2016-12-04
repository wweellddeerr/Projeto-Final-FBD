package br.unb.ppca.fbd.etl.dominio;

public enum UF {
	/**
	 * 
	insert into UF(SIGLA, NOME) VALUES ('AC', 'Acre');
	insert into UF(SIGLA, NOME) VALUES ('AL', 'Alagoas');
	insert into UF(SIGLA, NOME) VALUES ('AM', 'Amazonas');
	insert into UF(SIGLA, NOME) VALUES ('AP', 'Amap�');
	insert into UF(SIGLA, NOME) VALUES ('BA', 'Bahia');
	insert into UF(SIGLA, NOME) VALUES ('CE', 'Cear�');
	insert into UF(SIGLA, NOME) VALUES ('DF', 'Distrito Federal');
	insert into UF(SIGLA, NOME) VALUES ('ES', 'Esp�rito Santo');
	insert into UF(SIGLA, NOME) VALUES ('GO', 'Goi�s');
	insert into UF(SIGLA, NOME) VALUES ('MA', 'Maranh�o');
	insert into UF(SIGLA, NOME) VALUES ('MG', 'Minas Gerais');
	insert into UF(SIGLA, NOME) VALUES ('MS', 'Mato Grosso do Sul');
	insert into UF(SIGLA, NOME) VALUES ('MT', 'Mato Grosso');
	insert into UF(SIGLA, NOME) VALUES ('PA', 'Par�');
	insert into UF(SIGLA, NOME) VALUES ('PB', 'Para�ba');
	insert into UF(SIGLA, NOME) VALUES ('PE', 'Pernambuco');
	insert into UF(SIGLA, NOME) VALUES ('PI', 'Piau�');
	insert into UF(SIGLA, NOME) VALUES ('PR', 'Paran�');
	insert into UF(SIGLA, NOME) VALUES ('RJ', 'Rio de Janeiro');
	insert into UF(SIGLA, NOME) VALUES ('RN', 'Rio Grande do Norte');
	insert into UF(SIGLA, NOME) VALUES ('RO', 'Rond�nia');
	insert into UF(SIGLA, NOME) VALUES ('RR', 'Roraima');
	insert into UF(SIGLA, NOME) VALUES ('RS', 'Rio Grande do Sul');
	insert into UF(SIGLA, NOME) VALUES ('SC', 'Santa Catarina');
	insert into UF(SIGLA, NOME) VALUES ('SE', 'Sergipe');
	insert into UF(SIGLA, NOME) VALUES ('SP', 'S�o Paulo');
	insert into UF(SIGLA, NOME) VALUES ('TO', 'Tocantins');
	insert into UF(SIGLA, NOME) VALUES ('ZZ', 'Exterior');
	 * 
	 * 
	 */

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
