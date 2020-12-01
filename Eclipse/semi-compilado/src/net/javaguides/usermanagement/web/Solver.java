package net.javaguides.usermanagement.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
	
	// CPF de entrada na forma \d{11}
	private static boolean digitosVerificadoresOk(String cpf)
	{
		String dig1 = cpf.substring(9, 9);
		String dig2 = cpf.substring(10, 10);

		String parte1 = cpf.substring(0, 9);
		String parte2 = cpf.substring(0, 10);
		
		if(digitoVerificador(1, parte1).equals(dig1) && digitoVerificador(2, parte2).equals(dig2)) return true;			
		return false;
	}

	private static String digitoVerificador(int i, String subcpf) {
		
		if(i == 1)
		{
			
		}else if(i == 2)
		{
			
		}
		
		return null;
	}

	// Verificar formato de cpf e digito verificador
	public static String formatCpf(String cpf)
	{
	    Pattern pattern = Pattern.compile("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})\\-?(\\d{2})");//, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(cpf);
	    boolean matchFound = matcher.find();
	    
	    if(matchFound) {
	      System.out.println("Match found");
	      
	      	String formattedCpf = "";
		    
		    System.out.println("group matcher: " + matcher.group());
		    for(int i = 1; i < matcher.groupCount(); i++)
		    {
		    	System.out.println("group(" + i + ") : " + matcher.group(i));
		    	formattedCpf += matcher.group(i);
		    }
		    
		    System.out.println("Results: " + matcher.results() + "\n" + matcher.toString());
		    
		    return formattedCpf;
	    } else {
	      System.out.println("Match not found");
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
	}
	
}
