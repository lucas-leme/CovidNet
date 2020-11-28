package net.javaguides.usermanagement.dao;

import java.util.Arrays;
import java.util.List;

import net.javaguides.usermanagement.model.Hospital;

public class HospitalDAO {

	public List<String> selectCidades() {
		// TODO Auto-generated method stub
		List<String> ad_hoc = Arrays.asList(new String[]{"Sao Paulo", "Diadema", "Santo Andre"});
		return ad_hoc;
	}

	public List<Hospital> selectHospitais(String cidade) {
		// TODO Auto-generated method stub
		Hospital aux = new Hospital("Sao Luis");
		List<Hospital> hospitais = Arrays.asList(new Hospital[] {aux});
		
		return hospitais;
	}
}
