package de.telekom.sea2.persistance;

import java.io.File;
import java.util.List;
import static de.telekom.sea2.lookup.Salutation.*;


import de.telekom.sea2.model.Person;
import de.telekom.sea2.seminar.BaseObject;
import java.sql.*;

public class PersonRepository extends BaseObject {

	private Connection connection;
   
	
	public PersonRepository (Connection co) {         // neuer Konstruktor mit Übergabe
		connection = co;		
	}
	
	
	public boolean create (Person p) throws SQLException{   // schreibt neue Person in DB
	   
	   Statement statement = connection.createStatement();
	   String vorname = p.getVorname();
	   String nachname = p.getNachname();
	   Long id= p.getKundennummer();
	   
	   String insertString = "insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE ("+id+",1,'"+vorname+"','"+nachname+"')";
	   // Handling mit Enum muss noch gemacht werden!!!
	   try {
	     statement.executeQuery(insertString);   
	     statement.close();
	     return true;
	   } catch (SQLException sqlException) {
		   sqlException.printStackTrace();
	       return false;
	   }
	}
	
	public Person get (long id) throws SQLException {        // liest Person mit id aus DB
			
		Statement statement = connection.createStatement();
		String selectString="select * from personen where ID='"+id+"'";
		ResultSet resultSet = statement.executeQuery(selectString);

		Person p = new Person();
		if (resultSet.next()) {
//		   p.setAnrede(resultSet.getString(1));
		   p.setAnrede(HERR);
		   p.setKundennummer(resultSet.getLong(1));
		   p.setVorname(resultSet.getString(3));
		   p.setNachname(resultSet.getString(4));
  
		}
		 
//		while (resultSet.next()){
//		   System.out.println(resultSet.getString(1));
//		   System.out.println(resultSet.getString(2));
//		   System.out.println(resultSet.getString(3));
//		   System.out.println(resultSet.getString(4));		      
//		}
        
		resultSet.close();
		statement.close();
		
		return p;
	}
	
	public List getAll() {               // liest alle Personen aus DB
		return null;
	}
	
	
	public Boolean delete(long id) throws SQLException  {        // löscht Person mit id aus DB
		Statement statement = connection.createStatement();
		String delString="delete from personen where ID="+id+";";
		
		try {
			statement.execute(delString);
			statement.close();
			return true;
		} catch (Exception e) {return false;}
				
	}
    
	public Boolean delete(Person person) throws SQLException   {  // löscht Person aus DB
		Long id = person.getKundennummer();
		Statement statement = connection.createStatement();
		String delString="delete from personen where ID="+id+";";
		try {
			statement.execute(delString);
			statement.close();
			return true;
		} catch (Exception e) {return false;}
	}
	
    public boolean update (Person person) {  // macht eine Namensänderung einer bestehenden Person
		return true;
	}
}
