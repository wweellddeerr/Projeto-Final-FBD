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
having count(*) = 0
order by 1, 2, 3, 4;
