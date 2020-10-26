package net.javaguides.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Relatorio;
import net.javaguides.usermanagement.model.RelatorioHospitalar;

public class RelatorioHospitalarDAO {

	
	public List<RelatorioHospitalar> relatorios = new ArrayList<>();
	
	public RelatorioHospitalarDAO() {
		this.relatorios.add(new RelatorioHospitalar("Eistein"));
	}

	public void insertRelatorio(RelatorioHospitalar relatorio)  {
		// get the id of the last element		
		int new_id = this.relatorios.size();
		new_id = this.relatorios.get(new_id-1).getId();
		
		relatorio.setId(new_id+1);
		this.relatorios.add(relatorio);
	}

	public RelatorioHospitalar selectRelatorio(int id) {
		RelatorioHospitalar relatorio = null;
		//user = users.get(id);
		//return user;
		
		for(RelatorioHospitalar f_user : this.relatorios) { 
			   if(f_user.getId() == id) { 
			       relatorio = f_user;
			   }
			}
		return relatorio;
	}

	public List<RelatorioHospitalar> selectAllRelatorios() {

		return this.relatorios;
	}

	public boolean deleteRelatorio(int id){
		boolean rowDeleted = false;
	
		for(Relatorio f_user : this.relatorios) { 
			   if(f_user.getId() == id) { 
				   this.relatorios.remove(f_user);
				   rowDeleted = true;
				   break;
			   }
			}
		
		return rowDeleted;
	}

	public boolean updateRelatorio(RelatorioHospitalar relatorio) {
		boolean rowUpdated = false;
		
		for(RelatorioHospitalar f_user : this.relatorios) { 
			if(f_user.getId() == relatorio.getId()) { 
				
//				f_user.setName(user.getName());
//				f_user.setEmail(user.getEmail());
//				f_user.setCountry(user.getCountry());
				
				f_user.setNomeHospital(relatorio.getNomeHospital());		
					
				rowUpdated = true;
				break;
			}
		}
		
		return rowUpdated;
	}

}
