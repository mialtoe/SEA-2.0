package de.telekom.sea2.persistance;


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
			
//		Statement statement = connection.createStatement();
		String selectString="select * from personen where ID=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectString);){

     	  preparedStatement.setLong(1, id);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  
		  Person p = new Person();
		  if (resultSet.next()) {
		     p.setKundennummer(resultSet.getLong(1));
		     p.setAnrede(resultSet.getByte(2));
		     p.setVorname(resultSet.getString(3));
		     p.setNachname(resultSet.getString(4));
    	  }
	        
		  resultSet.close();
		  return p;
		}catch (Exception e) {return null;}
//		statement.close();
		
		
	}
	
	public int getMaxId () throws SQLException {            // ermittelt die höchste aktuelle ID (Kundennummer)
	     	
		
	    int maxId=0;
//		Statement statement = connection.createStatement();
	    String selectString = "select * from personen order by ID desc";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectString);){
		   try {
			   ResultSet resultSet = preparedStatement.executeQuery(selectString);
			   resultSet.next();
			   maxId = resultSet.getInt(1);   // höchste Kundennummer
			
		       return maxId;
		   } catch (Exception e) {return -1;}
	    } catch (Exception e) {return -1;}
	}
	
    public int getCountDB () throws SQLException {            // ermittelt die höchste aktuelle ID (Kundennummer)
	     	
		int countRes=-1;
		String selectString = "select count (*) from personen";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(selectString);){
		   try {
			   ResultSet resultSet = preparedStatement.executeQuery(selectString);
			   resultSet.next();
			   countRes = resultSet.getInt(1);   // Anzahl Einträge in DB
			
		       return countRes;
		   } catch (Exception e) {return -1;}
	    } catch (Exception e) {return -1;}
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
			    resultSet.beforeFirst();   // wieder zurück in vor die erste Reihe
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
	
	public Person[] searchP(String search) throws Exception {               // liest alle Personen aus DB
//		String selectString = "select * from personen where vorname like ('%?%')";
		String selectString = "select * from personen where vorname like ('%"
				                 +search
				                 +"%') or nachname like ('%"
				                 +search
				                 +"%')";
//		System.out.println(selectString);
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectString);){
		
		  try {
			  int size = 0;
//			  preparedStatement.setString(1, search);
		      preparedStatement.executeQuery();
			  ResultSet resultSet = preparedStatement.executeQuery(selectString);
			  if (resultSet != null) 
			  {
			    resultSet.last();    // geht in die letzte Reihe
			    size = resultSet.getRow(); // um die wievielte Reihe handelt es sich? 
			    resultSet.beforeFirst();   // wieder zurück in vor die erste Reihe
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
				System.out.println("XXX"+resultSet.getString(3));
			  }
			
			return personen;
			
			
		  } catch (Exception e) {}
		} catch (Exception e) {}
		return null;
	}
	
	
	public Boolean delete(long id) throws SQLException  {        // löscht Person mit id aus DB
		String delString="delete from personen where ID=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(delString);){
		      preparedStatement.setLong(1, id);
		      preparedStatement.executeQuery();
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
		      preparedStatement.executeQuery();
		   } catch (SQLException sqlException) {
			   sqlException.printStackTrace();
		       return false;
		   }
		   return true;
	}
	
    public boolean update (Person person) throws SQLException   {  // macht eine Namensänderung einer bestehenden Person
    	
//		Statement statement = connection.createStatement();
		
//		String updateString="update personen "                             // ohne prepareStatement
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
		      preparedStatement.executeQuery();
		   } catch (SQLException sqlException) {
			   sqlException.printStackTrace();
		       return false;
		   }
		   return true;
    	
	}
}
