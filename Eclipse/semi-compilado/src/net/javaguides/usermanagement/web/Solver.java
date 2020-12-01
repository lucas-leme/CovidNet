package net.javaguides.usermanagement.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {

	// Verificar formato de cpf e digito verificador
	public static String formatCpf(String cpf)
	{
	    Pattern pattern = Pattern.compile("(\\d{3})\\.?(\\d{3})\\.?(\\d{3})\\-?(\\d{2})");//, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(cpf);
	    boolean matchFound = matcher.find();
	    
	    if(matchFound) {
	      System.out.println("Match found");
		    
		    System.out.println("group matcher: " + matcher.group());
		    for(int i = 0; i < matcher.groupCount(); i++)
		    {
		    	System.out.println("group(" + i + ") : " + matcher.group(i));
		    }
		    
		    System.out.println("Results: " + matcher.results() + "\n" + matcher.toString());
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
