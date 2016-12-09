
	create view agrupamento_partido_eleitos as
	select CAST(ca.numero_partido AS CHAR(100)) AS PARTIDO, count(*)
	from candidatura ca
	inner join candidato c
	on c.cpf = ca.cpf_candidato
	where ca.SITUACAO_ELEICAO like 'ELEITO%'
	group by ca.numero_partido
	having count(*) > 1000
	order by 2 desc, 1;	
	
	create view vw_agrupamento_cargo_sexo as
	select cr.ano_eleicao, cr.cargo, c.sexo, count(*)
	from candidatura cr
	inner join candidato c
	on c.cpf = cr.cpf_candidato
	group by cr.ano_eleicao, cr.cargo, c.sexo
	order by 1, 2, 3;

	create view vw_ocupacao_que_nao_elege as
	select o.descricao as nome_ocupacao
	from ocupacao o
	where o.cod not in (
		select distinct c2.cod_ocupacao 
		from candidato c2
		inner join candidatura c3
		on c2.cpf = c3.cpf_candidato
		where c3.SITUACAO_ELEICAO like 'ELEITO%'
	)
	order by 1; 