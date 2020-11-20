select l.id, m.nome, e.nome, p.nome
from funcionarios f, 
	(	
		select f.nome 
		from medicos m
		inner join funcionarios f
		on f.id = m.funcionario_id
	) as m, 
	(
		select f.nome 
		from enfermeiros e
		inner join funcionarios f
		on f.id = e.funcionario_id
	) as e, 
	pacientes p, hospitais h, leitos2 l
	
select * from medicos;