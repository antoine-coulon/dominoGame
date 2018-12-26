package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DominoReader {
	
	public DominoReader(){}
	final File file = getResource("dominos.csv");
	   
	public static List<String> readFile(File file) throws IOException {

        List<String> result = new ArrayList<String>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(line);
        }

        br.close();
        fr.close();

        return result;
    }
	
	 public static String getResourcePath(String fileName) {
	       final File f = new File("");
	       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
	       return dossierPath;
	   }
	
	public static File getResource(String fileName) {
	       final String completeFileName = getResourcePath(fileName);
	       File file = new File(completeFileName);
	       return file;
	   }
	
	public void read(String fileName){
		   
	       
		   Path orderPath = Paths.get(fileName);
	        List<String> lines = null;
	        try {
	            lines = Files.readAllLines(orderPath);
	            if (lines.size() < 2) {
		            System.out.println("Il n'y a pas de dominos dans le fichier");
		            return;
		        }
		     
		        for (int i = 1; i < lines.size(); i++) {
		            String[] split = lines.get(i).split(",");
		            int nbCouronnesTuile1 = Integer.valueOf(split[0]);
		            String typeTuile1 = String.valueOf(split[1]);
		            int nbCouronnesTuile2 = Integer.valueOf(split[2]);
		            String typeTuile2 = String.valueOf(split[3]);
		            int numeroDomino = Integer.valueOf(split[4]);
		            
		            Tuile t1 = new Tuile(nbCouronnesTuile1, typeTuile1);
		            Tuile t2 = new Tuile(nbCouronnesTuile2, typeTuile2);
		            Domino d1 = new Domino(numeroDomino,t1,t2);
		            d1.remplirDominos(d1);
		            //d1.displayListeDominos();
		        }
		        
	        } catch (IOException e) {
	            System.out.println("Impossible de lire le fichier de dominos");
	        }
	        
	   }
	   
	 
	

}
