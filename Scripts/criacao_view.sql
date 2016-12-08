create view vw_agrupamento_cargo_sexo as
select cr.ano_eleicao, cr.cargo, c.sexo, count(*)
from candidatura cr
inner join candidato c
on c.cpf = cr.cpf_candidato
group by cr.ano_eleicao, cr.cargo, c.sexo
order by 1, 2, 3;



select m.cod_tse, cr.ano_eleicao, cr.cargo, c.sexo, count(*)
from candidatura cr
inner join candidato c
on c.cpf = cr.cpf_candidato
inner join municipio m
on m.cod_tse = cr.cod_tse_munic_eleicao
group by m.cod_tse, cr.ano_eleicao, cr.cargo, c.sexo
order by 1, 2, 3, 4;


select m.sigla_uf, count(*)
from municipio m
where m.cod_tse in (
	select distinct cr.cod_tse_munic_eleicao
	from candidatura cr
	inner join candidato c
	on cr.cpf_candidato = c.cpf
	where not exists (
		select *
		from candidatura cr2
		inner join candidato c2
		on cr2.cpf_candidato = c2.cpf
		where cr2.cpf_candidato = cr.cpf_candidato
		and cr2.ano_eleicao = cr.ano_eleicao
		and c2.cpf = c.cpf
		and c2.sexo = 'FEMININO'
	)
)
group by m.sigla_uf;

select count(distinct  cr.cod_tse_munic_eleicao)
	from candidatura cr
	inner join candidato c
	on cr.cpf_candidato = c.cpf
	where not exists (
		select *
		from candidatura cr2
		inner join candidato c2
		on cr2.cpf_candidato = c2.cpf
		where cr2.cpf_candidato = cr.cpf_candidato
		and cr2.ano_eleicao = cr.ano_eleicao
		and c2.cpf = c.cpf
		and c2.sexo = 'FEMININO'
	)