package de.telekom.sea2.persistance;

//import java.io.File;
//import java.util.List;
//import static de.telekom.sea2.lookup.Salutation.*;


import de.telekom.sea2.model.Person;
import de.telekom.sea2.seminar.BaseObject;
import java.sql.*;

public class PersonRepository extends BaseObject {

	private Connection connection;
   
	
	public PersonRepository (Connection co) {         // neuer Konstruktor mit Übergabe
		connection = co;		
	}
	
	
	public boolean create (Person p) throws SQLException{   // schreibt neue Person in DB
	      
	   String vorname = p.getVorname();
	   String nachname = p.getNachname();
	   Byte anrede = p.getAnrede().toByte();
	   Long id= p.getKundennummer();
	   
	   String insertString = "insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUE (?,?,?,?)";
	   	   
	   try (PreparedStatement preparedStatement = connection.prepareStatement(insertString);){
	      preparedStatement.setLong(1, id);
	      preparedStatement.setInt(2, anrede);
	      preparedStatement.setString(3, vorname);
	      preparedStatement.setString(4, nachname);
	      preparedStatement.execute();
	   } catch (SQLException sqlException) {
		   sqlException.printStackTrace();
	       return false;
	   }
	   return true;
	}
	
	public Person get (long id) throws SQLException {        // liest Person mit id aus DB
			
		Statement statement = connection.createStatement();
		String selectString="select * from personen where ID='"+id+"'";
		ResultSet resultSet = statement.executeQuery(selectString);

		Person p = new Person();
		if (resultSet.next()) {
//		   p.setAnrede(resultSet.getString(1));
		   p.setKundennummer(resultSet.getLong(1));
		   p.setAnrede(resultSet.getByte(2));
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
	
	public int getMaxId () throws SQLException {            // ermittelt die höchste aktuelle ID (Kundennummer)
	     	
	    int maxId=0;
		Statement statement = connection.createStatement();
		String selectString = "select * from personen order by ID desc";
		try {
			ResultSet resultSet = statement.executeQuery(selectString);
			resultSet.next();
			maxId = resultSet.getInt(1);   // höchste Kundennummer
			
		    return maxId;
		} catch (Exception e) {return 0;}
	}
	
	public Person[] getAll() throws Exception {               // liest alle Personen aus DB
//		Statement statement = connection.createStatement();
		String selectString = "select * from personen order by ID desc";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectString);){
		
		  try {
			  int size = 0;
			  ResultSet resultSet = preparedStatement.executeQuery(selectString);
			  if (resultSet != null) 
			  {
			    resultSet.last();    // geht in die letzte Reihe
			    size = resultSet.getRow(); // um die wievielte Reihe handelt es sich? 
			    resultSet.beforeFirst();   // wieder zurück in vor die erste Reiehe
		      }
			
			  Person[] personen = new Person[size];   // Personenliste mit der Anzahl DB Einträgen
			
			  int i=0;
			  while (resultSet.next()) {
				Person p = new Person();
				p.setKundennummer(resultSet.getLong(1));
				p.setAnrede(resultSet.getByte(2));
				p.setVorname(resultSet.getString(3));
				p.setNachname(resultSet.getString(4));
				personen[i] = p;
				i++;
			  }
			
//			statement.close();
			return personen;
			
			
		  } catch (Exception e) {}
		} catch (Exception e) {}
		return null;
	}
	
	
	public Boolean delete(long id) throws SQLException  {        // löscht Person mit id aus DB
		String delString="delete from personen where ID=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(delString);){
		      preparedStatement.setLong(1, id);
		      preparedStatement.execute();
		   } catch (SQLException sqlException) {
			   sqlException.printStackTrace();
		       return false;
		   }
		   return true;
				
	}
    
	public Boolean delete(Person person) throws SQLException   {  // löscht Person aus DB
		String delString="delete from personen where ID=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(delString);){
		      preparedStatement.setLong(1, person.getKundennummer());
		      preparedStatement.execute();
		   } catch (SQLException sqlException) {
			   sqlException.printStackTrace();
		       return false;
		   }
		   return true;
	}
	
    public boolean update (Person person) throws SQLException   {  // macht eine Namensänderung einer bestehenden Person
    	
//		Statement statement = connection.createStatement();
		
//		String updateString="update personen "
//		                       + "SET ANREDE=" + person.getAnrede().toByte()
//		                       + ",VORNAME='" + person.getVorname()
//		                       + "', NACHNAME='" + person.getNachname()
//		                       + "' where ID =" + person.getKundennummer()+";";       // Update des Datensatz erfolgt über ID 
//		
		
		String updateString="update personen "
                + "SET ANREDE=?, "
                + "VORNAME=?, "
                + "NACHNAME=? "
                + " where ID =?";       // Update des Datensatz erfolgt über ID 

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateString);){
		      preparedStatement.setInt(1, person.getAnrede().toByte());
		      preparedStatement.setString(2, person.getVorname());
		      preparedStatement.setString(3, person.getNachname());
		      preparedStatement.setLong(4, person.getKundennummer());
		      preparedStatement.execute();
		   } catch (SQLException sqlException) {
			   sqlException.printStackTrace();
		       return false;
		   }
		   return true;
    	
	}
}
