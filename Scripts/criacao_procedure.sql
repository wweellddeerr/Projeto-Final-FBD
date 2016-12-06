DELIMITER //
CREATE PROCEDURE incluir_ufs(OUT retorno char(100))
BEGIN
	DECLARE NRO_UFS_CAD INT;
	SELECT COUNT(SIGLA) INTO NRO_UFS_CAD FROM UF;
	CASE
		WHEN NRO_UFS_CAD > 0 THEN
			SET retorno = 'Já havia UF cadastrada, inclusão NÃO realizada';
			
		ELSE
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
			SET retorno = 'Não havia UF cadastrada, inclusão realizada com sucesso';
	END CASE;
END//