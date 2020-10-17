package net.javaguides.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Paciente;

public class PacienteDAO {

	public List<Paciente> pacientes = new ArrayList<>();
	
	public PacienteDAO() {
		this.pacientes.add(new Paciente(1, "111.111.111-00","Pedro","123", "12/12/2010"));
		this.pacientes.add(new Paciente(2, "222.222.222-00","Joao","456", "12/12/2010"));
		this.pacientes.add(new Paciente(3, "333.333.333-00","Maria","789", "12/12/2010"));
	}

	public void insertPaciente(Paciente paciente)  {
		// get the id of the last element		
		int new_id = this.pacientes.size();
		if (new_id != 0) {
			new_id = this.pacientes.get(new_id-1).getId();
		}
		paciente.setId(new_id+1);
		this.pacientes.add(paciente);
	}

	public Paciente selectPaciente(int id) {
		Paciente paciente = null;
		
		for(Paciente f_paciente : this.pacientes) { 
		   if(f_paciente.getId() == id) { 
			   paciente = f_paciente;
		   }
		}
		return paciente;
	}

	public List<Paciente> selectAllPacientes() {
		return this.pacientes;
	}

	public boolean deletePaciente(int id){
		boolean rowDeleted = false;

		for(Paciente f_paciente : this.pacientes) { 
			if(f_paciente.getId() == id) {
			   this.pacientes.remove(f_paciente);
			   rowDeleted = true;
			   break;
			}
		}
		
		return rowDeleted;
	}

	public boolean updatePaciente(Paciente paciente) {
		boolean rowUpdated = false;
		
		for(Paciente f_paciente : this.pacientes) { 
			if(f_paciente.getId() == paciente.getId()) { 
				
				f_paciente.setCpf(paciente.getCpf());
				f_paciente.setNome(paciente.getNome());
				f_paciente.setProntuario(paciente.getProntuario());
				f_paciente.setDataDeEntrada(paciente.getDataDeEntrada());
				
				rowUpdated = true;
				break;
			}
		}
		
		return rowUpdated;
	}

}
