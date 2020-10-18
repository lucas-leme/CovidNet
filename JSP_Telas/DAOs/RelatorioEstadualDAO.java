package net.javaguides.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Relatorio;
import net.javaguides.usermanagement.model.RelatorioEstadual;

public class RelatorioEstadualDAO {

	
	public List<RelatorioEstadual> relatorios = new ArrayList<>();
	
	public RelatorioEstadualDAO() {
		this.relatorios.add(new RelatorioEstadual("São Paulo", "500", "5000"));
		this.relatorios.add(new RelatorioEstadual("Rio de Janeiro", "400", "3000"));
		this.relatorios.add(new RelatorioEstadual("São Paulo", "500", "5000"));

	}

	public void insertRelatorio(RelatorioEstadual relatorio)  {
		// get the id of the last element		
		int new_id = this.relatorios.size();
		new_id = this.relatorios.get(new_id-1).getId();
		
		relatorio.setId(new_id+1);
		this.relatorios.add(relatorio);
	}

	public RelatorioEstadual selectRelatorio(int id) {
		RelatorioEstadual relatorio = null;
		//user = users.get(id);
		//return user;
		
		for(RelatorioEstadual f_user : this.relatorios) { 
			   if(f_user.getId() == id) { 
			       relatorio = f_user;
			   }
			}
		return relatorio;
	}

	public List<RelatorioEstadual> selectAllRelatorios() {

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

	public boolean updateRelatorio(RelatorioEstadual relatorio) {
		boolean rowUpdated = false;
		
		for(RelatorioEstadual f_user : this.relatorios) { 
			if(f_user.getId() == relatorio.getId()) { 
				
						
				f_user.setNomeEstado(relatorio.getNomeEstado());
				f_user.setNumeroHospitais(f_user.getNumeroHospitais());
				f_user.setNumeroMunicipios(f_user.getNumeroMunicipios());

				
				rowUpdated = true;
				break;
			}
		}
		
		return rowUpdated;
	}

}
