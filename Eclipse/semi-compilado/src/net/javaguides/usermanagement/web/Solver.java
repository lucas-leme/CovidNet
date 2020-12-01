package net.javaguides.usermanagement.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
	
	// CPF de entrada na forma \d{11}
	private static boolean digitosVerificadoresOk(String cpf)
	{
		String dig1 = cpf.substring(9, 10); // so o 10o
		String dig2 = cpf.substring(10, 11);

		String parte1 = cpf.substring(0, 9); // ate o 9o
		String parte2 = cpf.substring(0, 10);
		
		if(digitoVerificador(1, parte1).equals(dig1) && digitoVerificador(2, parte2).equals(dig2)) return true;			
		return false;
	}

	private static String digitoVerificador(int pos_dig, String subcpf) 
	{
		int peso = 0;
		
		if(pos_dig == 1) peso = 10;
		else if(pos_dig == 2) peso = 11;
		else return null;
		
		int verif = 0;
		
		for(char dig : subcpf.toCharArray())
		{
			//System.out.println(verif + " += " + peso + " * " + Character.getNumericValue(dig));
			verif += peso * Character.getNumericValue(dig);
			peso--;
		}
		
		System.out.println("ver antes: " + verif);
		
		verif  = 11 - (verif % 11);
		if(verif >= 10) verif = 0;
		
		System.out.println("digito verificador " + pos_dig + "  = " + verif);
		
		return Integer.toString(verif);
	}

	// Verificar formato de cpf e digito verificador
	public static String formatCpf(String cpf)
	{
	    Pattern pattern = Pattern.compile("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})\\-?(\\d{2})");//, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(cpf);
	    boolean matchFound = matcher.find();
	    
	    if(matchFound) {
	      System.out.println("Match found");
	      
	      	String formattedCpf = cpf.replaceAll("[\\.\\-]", "");
	      	System.out.println("Formatted: " + formattedCpf);
		    
		    System.out.println("Results: " + matcher.results() + "\n" + matcher.toString());
		    
		    System.out.println("\n\n");
		    
		    if(!digitosVerificadoresOk(formattedCpf)) return null;
		    return formattedCpf;
	    } else {
	      System.out.println("Match not found\n\n");
	    }
		
		return null;
	}
	
	// Verificar formatacao de data
	public static boolean isDateOk(String date)
	{
		return false;
	}
	
	// Verificar nomes de pessoas, ruas, etc
	public static boolean isNameOk(String name)
	{
		return false;
	}
	
	public static void main(String[] args)
	{
		System.out.println("CPF OK ? " + formatCpf("123.456.789-00"));
		System.out.println("CPF OK ? " + formatCpf("083.955.01200"));
		System.out.println("CPF OK ? " + formatCpf("123.456789-00"));
		System.out.println("CPF OK ? " + formatCpf("153."));
		System.out.println("CPF OK ? " + formatCpf("3546.absa244")); 
		System.out.println("CPF OK ? " + formatCpf(""));
		
		// explicacao : https://campuscode.com.br/conteudos/o-calculo-do-digito-verificador-do-cpf-e-do-cnpj
		System.out.println("CPF OK ? " + formatCpf("145.382.206-20"));
		System.out.println("CPF OK ? " + formatCpf("145382206-20"));
		
		// https://www.4devs.com.br/gerador_de_cpf
		System.out.println("CPF OK ? " + formatCpf("834.205.782-88"));
		System.out.println("CPF OK ? " + formatCpf("271.520.005-65"));
		System.out.println("CPF OK ? " + formatCpf("603.462.186-08"));
	}
	
}
