select * from medicos;

select * from enfermeiros;

select * from funcionarios;

select f.nome 
from medicos m
inner join funcionarios f
on f.id = m.funcionario_id

select * 
from enfermeiros e
inner join funcionarios f
on f.id = e.funcionario_id

select * from pacientes;

select * from leitos2;

select l.id id, m.nome medico, e.nome enfermeiro, p.nome paciente
from
	leitos2 l
	inner join (	
		select f.nome, m.id 
		from medicos m
		inner join funcionarios f
		on f.id = m.funcionario_id
	) as m
	on m.id = l.medico_id 
	inner join (
		select f.nome, e.id
		from enfermeiros e
		inner join funcionarios f
		on f.id = e.funcionario_id
	) as e
	on e.id = l.enfermeiro_id
	inner join pacientes p
	on p.id = l.paciente_id 
	where l.id = ?

