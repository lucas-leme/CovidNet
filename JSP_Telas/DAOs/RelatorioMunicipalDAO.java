package net.javaguides.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Relatorio;
import net.javaguides.usermanagement.model.RelatorioMunicipal;

public class RelatorioMunicipalDAO {

	
	public List<RelatorioMunicipal> relatorios = new ArrayList<>();
	
	public RelatorioMunicipalDAO() {
		this.relatorios.add(new RelatorioMunicipal("Santo André", 30));
	}

	public void insertRelatorio(RelatorioMunicipal relatorio)  {
		// get the id of the last element		
		int new_id = this.relatorios.size();
		new_id = this.relatorios.get(new_id-1).getId();
		
		relatorio.setId(new_id+1);
		this.relatorios.add(relatorio);
	}

	public RelatorioMunicipal selectRelatorio(int id) {
		RelatorioMunicipal relatorio = null;
		//user = users.get(id);
		//return user;
		
		for(RelatorioMunicipal f_user : this.relatorios) { 
			   if(f_user.getId() == id) { 
			       relatorio = f_user;
			   }
			}
		return relatorio;
	}

	public List<RelatorioMunicipal> selectAllRelatorios() {

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

	public boolean updateRelatorio(RelatorioMunicipal relatorio) {
		boolean rowUpdated = false;
		
		for(RelatorioMunicipal f_user : this.relatorios) { 
			if(f_user.getId() == relatorio.getId()) { 
				
//				f_user.setName(user.getName());
//				f_user.setEmail(user.getEmail());
//				f_user.setCountry(user.getCountry());
				

				f_user.setNomeMunicipio(relatorio.getNomeMunicipio());
				f_user.setNumeroHospitais(relatorio.getNumeroHospitais());
				
				rowUpdated = true;
				break;
			}
		}
		
		return rowUpdated;
	}

}
