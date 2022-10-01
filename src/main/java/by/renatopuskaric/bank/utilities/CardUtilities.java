package by.renatopuskaric.bank.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

import org.apache.tomcat.util.http.fileupload.FileUtils;

public class CardUtilities {
	
	
	public static void makeCard(String cardName, String cardContent) {
		
		
		File file = new File("cards\\" + cardName + ".txt"); 

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            String contents = cardContent;

            writer.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static boolean isCardExisting(String cardName) {

        File root = new File("C:\\Users\\Listopad\\Downloads\\Bank\\cards");
        //String fileName = "Privremeno.txt";
        String[] fList = {};

        if (root.isDirectory()){
            fList = root.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(cardName) && name.endsWith("txt");

                }
            });
        }
        boolean exist = false;
        String result = "no result";
        assert fList != null;
        for (String fName : fList) {
            if (fName.equals(cardName + ".txt")) {
                result = "Datoteka već postoji" + fName;
                exist = true;
            }else {
                result = "Ne postoji datoteka s tim imenom " + fName;
                exist = false;
            }
        }
        
        return exist;
		
	}
	
	public static void makeInactiveIfDeleted(String oib) throws IOException {
		String userName = System.getProperty("user.name");
		File folder = new File("C:\\Users\\"+ userName + "\\Downloads\\Bank\\cards\\");
        
        File[] fList = folder.listFiles();
        
        
        for (int i = 0; i < fList.length; i++) {

            if (fList[i].isFile()) {

            	String fileName = fList[i].getName().toString();
            	fileName.substring(0, fileName.indexOf("-"));
                if (fileName.contains(oib)) {
					File f = new File("C:\\Users\\"+ userName + "\\Downloads\\Bank\\cards\\" + fList[i].getName());
					f.renameTo(new File("C:\\Users\\"+ userName + "\\Downloads\\Bank\\cards\\" + "INACTIVE-" + fList[i].getName().toString() + ".txt"));
				}
            }
        }

        System.out.println("conversion is done");
        
        

		/*
		 * if (root.isDirectory()){ fList = root.list(new FilenameFilter() {
		 * 
		 * @Override public boolean accept(File dir, String name) { return
		 * name.startsWith(oib) && name.endsWith("txt");
		 * 
		 * } }); }
		 * 
		 * assert fList != null; for (String fName : fList) { if (fName.contains(oib)) {
		 * new File(fName).renameTo(new File("INACTIVE" + fName)); } }
		 * 
		 * if (root.isDirectory()){ fList = root.list(new FilenameFilter() {
		 * 
		 * @Override public boolean accept(File dir, String name) { return
		 * name.startsWith(oib) && name.endsWith("txt");
		 * 
		 * } }); }
		 */
        
	}

}
