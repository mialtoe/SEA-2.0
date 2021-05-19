package de.telekom.sea2;

import de.telekom.sea2.seminar.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { 
	          SeminarApp app=SeminarApp.getRootApp();
	                 
	          app.setParent(null);
	          app.run(args);
	       }
	       catch (Exception anyException) {
	    	   System.out.println("Es ist ein Fehler aufgetreten, deshalb wird das Programm jetzt kontrolliert beendet.");
	    	   anyException.printStackTrace();
	       }
		
	}

}
