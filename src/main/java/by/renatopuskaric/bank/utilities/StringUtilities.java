package by.renatopuskaric.bank.utilities;

public class StringUtilities {
	
	public static String formatForFileName(String string) {
		
		String fileNameString = string.replaceAll("[:.+ ]", "-");
		
		return fileNameString;
	}

}
