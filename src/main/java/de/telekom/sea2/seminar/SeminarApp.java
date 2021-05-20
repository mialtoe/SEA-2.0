package de.telekom.sea2.seminar;

import java.io.IOException;
import de.telekom.sea2.ui.*;


//import de.telekom.sea2.seminar.BaseObject;
import de.telekom.sea2.model.Person;
//import static de.telekom.sea2.lookup.Salutation.*;
import de.telekom.sea2.persistance.PersonRepository;
import java.sql.*;

//import de.telekom.sea.seminar.Menu;
//import de.telekom.sea.seminar.MyList;
//import de.telekom.sea.seminar.MyMenu;

//import de.telekom.sea.seminar.VerwaltungsGruppe;

public class SeminarApp extends BaseObject{
	
	private static SeminarApp theInstance;
	 
	 private SeminarApp() {    // constructor
	 }
	  
	 public void run(String[] args) throws IOException, ClassNotFoundException {
 	    Person child= new Person();
 	    child.setParent(this);
 	
 	    
 	   Class.forName("org.mariadb.jdbc.Driver");

 	   try {

		   Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/seadb","seauser","seapass");
		   
		   PersonRepository personRepository = new PersonRepository(connection);
           
		   Menu menu = new Menu(personRepository);   // neuer Konstruktor mit Parameter // try with ressources!
		   menu.keepAsking();
			
		   
//		   Testblock Teilnehmer in DB schreiben:
//		   *************************************
//		   Person teilnehmer1 = new Person("Pauline","Meier",99,FRAU);
//		   Person teilnehmer1 = new Person("Hans","Meiser",98,HERR);
		   
//		    Boolean cr = personRepository.create(teilnehmer1);
		   
		   
//		   Testblock Teilnehmer aus DB lesen:
//		   *************************************
		   
//		   Person p1 = personRepository.get(98);
//		   
//		   if (p1.getKundennummer() != null)
//           	   System.out.println(p1.getVorname()+" "+p1.getNachname()+" "+p1.getKundennummer()+" "+p1.getAnrede());
//		   else
//			   System.out.println("Kunde mit ID nicht gefunden");
//		   
//		   
//		   Person[] pListe = personRepository.getAll();
//		   System.out.println("Aktuelle Komplettliste in DB");
//		   for (int i=0; i < pListe.length; i++) {
//			   try {
//					Person p = (Person) pListe[i];   // zeigt nur gültige Einträge an
//					System.out.println(i + ".: " + p.getAnrede() + " " + p.getVorname() + " " + p.getNachname()+" Kundennummer: "+p.getKundennummer());
//				}
//				catch (RuntimeException re) {    // wenn Null Eintrag in Liste oder ungültig
//				   System.out.println("Index "+i+" kein gültiger Eintrag gefunden");
//				}
//		   }
//		   
//		   
//		   System.out.println(" hoechsteKDNR in DB:" + personRepository.getMaxId());
//		   
//		   
//		   Testblock Update Teilnehmer in DB:
//		   *************************************
//		   Person updateP = new Person("Hans","Meiser",66,HERR);
//		   Boolean cr = personRepository.update(updateP);
		   
		   
//		   Testblock Teilnehmer löschen aus DB :
//		   *************************************
//		    int id = 99;
//			personRepository.delete(id);
				   
//		   Testblock Teilnehmer löschen aus DB :
//		   *************************************
//		   Person teilnehmer1 = new Person("Pauline","Meier",99,HERR);
//		   personRepository.delete(teilnehmer1);
			   
		   
		   
		   
//		   Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/seadb?user=seauser&password=seapass");
//		   Statement statement = connection.createStatement();
//
////		   statement.executeQuery("insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE (3,1,'Karl','Napp')");
//		   
//		   String insertSQL = "insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE (?,?,?,?)";
//		   PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
//		   preparedStatement.setLong(1, 99);
//		   preparedStatement.setInt(2, 1);
//		   preparedStatement.setString(3, "Joachim");
//		   preparedStatement.setString(4, "Hansen");
//		   preparedStatement.execute();
		   
//		   statement.executeQuery("UPDATE personen SET Vorname='Hans' WHERE ID='1'");
		   
//		   ResultSet resultSet = statement.executeQuery("select * from personen");
// 
//		   
//		   while (resultSet.next()){
//		      System.out.println(resultSet.getString(1));
//		      System.out.println(resultSet.getString(2));
//		      System.out.println(resultSet.getString(3));
//		      System.out.println(resultSet.getString(4));		      
//		   }
//           
//		   resultSet.close();
		   
		   connection.close();

		}  catch (Exception e) {e.printStackTrace();}
		finally {}
      
    }
	
	 
	 
	 
	public static SeminarApp getRootApp () {
		if (theInstance == null) {
		    theInstance = new SeminarApp();
		}
		return theInstance;
	}
	
	
}

