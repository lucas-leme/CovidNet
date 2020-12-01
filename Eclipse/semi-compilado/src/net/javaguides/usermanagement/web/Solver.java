package net.javaguides.usermanagement.web;

public class Solver {

	// Verificar formato de cpf e digito verificador
	public static boolean isCpfOk(String cpf)
	{
	    Pattern pattern = Pattern.compile("(\d{3}\.?){2}\d{3}\-?\d{2}");//, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher("Solver de CPF");
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	      System.out.println("Match found");
	    } else {
	      System.out.println("Match not found");
	    }
		
		return false;
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
	
}
