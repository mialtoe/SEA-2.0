package de.telekom.sea2.seminar;

import java.io.IOException;


import de.telekom.sea2.seminar.BaseObject;
import de.telekom.sea2.model.Person;
import java.sql.*;

//import de.telekom.sea.seminar.Menu;
//import de.telekom.sea.seminar.MyList;
//import de.telekom.sea.seminar.MyMenu;
//import de.telekom.sea.seminar.SeminarApp;
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

//		   Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/seadb?user=seauser&password=seapass");
		   Statement statement = connection.createStatement();

//		   statement.executeQuery("insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE (3,1,'Karl','Napp')");
		   
		   String insertSQL = "insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE (?,?,?,?)";
		   PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
		   preparedStatement.setLong(1, 99);
		   preparedStatement.setInt(2, 1);
		   preparedStatement.setString(3, "Joachim");
		   preparedStatement.setString(4, "Hansen");
		   preparedStatement.execute();
		   
//		   statement.executeQuery("UPDATE personen SET Vorname='Hans' WHERE ID='1'");
		   
		   ResultSet resultSet = statement.executeQuery("select * from personen");
 
		   
		   while (resultSet.next()){
		      System.out.println(resultSet.getString(1));
		      System.out.println(resultSet.getString(2));
		      System.out.println(resultSet.getString(3));
		      System.out.println(resultSet.getString(4));		      
		   }
           
		   resultSet.close();
		   statement.close();
		   connection.close();

		}  catch (Exception e) {e.printStackTrace();}
		finally {}

 	    
 	    
 	    
 	    // VerwaltungsInterface teilnehmerGruppe=new VerwaltungsGruppeFrank();
//		MyList teilnehmerGruppe = new VerwaltungsGruppe();
 	    // VerwaltungsGruppe teilnehmerGruppe = new VerwaltungsGruppe();
		
 	    // MyMenu menu = new Menu(teilnehmerGruppe);    // neuer Konstruktor mit Parameter
		
		// Menu menu = new Menu(teilnehmerGruppe);    // neuer Konstruktor mit Parameter
		 
//		try (MyMenu menu = new Menu(teilnehmerGruppe)){    // neuer Konstruktor mit Parameter // try with ressources!
//		    teilnehmerGruppe.subscribe(menu);
//		    //Menu menu = new Menu();                 // alter Konstruktor ohne Parameter 
//		    //menu.setMyList(teilnehmerGruppe);
//	        menu.keepAsking();
//		}
		

      
    }
	
	 
	 
	 
	public static SeminarApp getRootApp () {
		if (theInstance == null) {
		    theInstance = new SeminarApp();
		}
		return theInstance;
	}
	
	
}

