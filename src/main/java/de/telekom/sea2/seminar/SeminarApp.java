package de.telekom.sea2.seminar;

import java.io.IOException;
import de.telekom.sea2.ui.*;


import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistance.PersonRepository;
import java.sql.*;


public class SeminarApp extends BaseObject{
	/**
	 * Klasse zum Aufruf der Haupt-Funktionalität
	 */
	private static SeminarApp theInstance;
	 
	/**
	 * Konstruktor
	 */
	 private SeminarApp() {    // constructor
	 }
	  
	 /**
	  * Main- Runmethode, erstellt Verbindung zur DB, erstellt ein neues Repository + Menü und hält dann das Menü am Leben 
	  * @param args - Main Parameter
	  * @throws IOException - wirft IOException
	  */
	 public void run(String[] args) throws IOException{
 	    Person child= new Person();
 	    child.setParent(this);
 	
 	   try { 	    
 	       Class.forName("org.mariadb.jdbc.Driver");
 	   }  catch (Exception e) {e.printStackTrace();}

 	   try {

		   Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/seadb","seauser","seapass");
		   
		   PersonRepository personRepository = new PersonRepository(connection);
           
		   Menu menu = new Menu(personRepository);   // neuer Konstruktor mit Parameter // try with ressources!
		   menu.keepAsking();
		   connection.close();

		}  catch (Exception e) {e.printStackTrace();}
		finally {};
      
    }
	
	 
	 
	 
	public static SeminarApp getRootApp () {
		if (theInstance == null) {
		    theInstance = new SeminarApp();
		}
		return theInstance;
	}
	
	
}

