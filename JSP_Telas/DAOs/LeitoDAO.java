package net.javaguides.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Leito;


public class LeitoDAO {

	
	public List<Leito> leitos = new ArrayList<>();
	
	public LeitoDAO() {
		this.leitos.add(new Leito(1,"Dr Hugo","Lucas", "Arthur" ));
		this.leitos.add(new Leito(2,"Dr Arthur","Hugo", "Lucas" ));
		this.leitos.add(new Leito(3,"Dr Lucas","Arthur", "Hugo" ));
		this.leitos.add(new Leito(4,"Dr Kechi","Lab", "Eng Soft" ));
	}

	public void insertLeito(Leito user)  {
		// get the id of the last element		
		int new_id = this.leitos.size();
		new_id = this.leitos.get(new_id-1).getId();
		
		user.setId(new_id+1);
		this.leitos.add(user);
	}

	public Leito selectLeito(int id) {
		Leito user = null;
		//user = leitos.get(id);
		//return user;
		
		for(Leito f_leito : this.leitos) { 
			   if(f_leito.getId() == id) { 
			       user = f_leito;
			   }
			}
		return user;
	}

	public List<Leito> selectAllLeitos() {

		return this.leitos;
	}

	public boolean deleteLeito(int id){
		boolean rowDeleted = false;
	
		for(Leito f_leito : this.leitos) { 
			   if(f_leito.getId() == id) { 
				   this.leitos.remove(f_leito);
				   rowDeleted = true;
				   break;
			   }
			}
		
		return rowDeleted;
	}

	public boolean updateLeito(Leito user) {
		boolean rowUpdated = false;
		
		for(Leito f_leito : this.leitos) { 
			if(f_leito.getId() == user.getId()) { 
				
				f_leito.setMedico(user.getMedico());
				f_leito.setPaciente(user.getPaciente());
				f_leito.setEnfermeiro(user.getEnfermeiro());
				
				rowUpdated = true;
				break;
			}
		}
		
		return rowUpdated;
	}

}
