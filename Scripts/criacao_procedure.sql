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
			(...)
			SET retorno = 'Não havia UF cadastrada, inclusão realizada com sucesso';
	END CASE;
END//



