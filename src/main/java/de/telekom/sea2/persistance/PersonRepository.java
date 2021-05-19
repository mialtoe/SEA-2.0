package de.telekom.sea2.persistance;

import java.util.List;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.seminar.BaseObject;

public class PersonRepository extends BaseObject {

	public boolean create (Person p) {   // schreibt neue Person in DB
	   return true;
	}
	
	public Person get (long id) {        // liest Person mit id aus DB
		return null;
	}
	
	public List getAll() {               // liest alle Personen aus DB
		return null;
	}
	
	
	public void delete(long id) {
		                                 // löscht Person mit id aus DB
	}
    
	public void delete(Person person) {  // löscht Person aus DB
		                                 
	}
	
    public boolean update (Person person) {  // macht eine Namensänderung einer bestehenden Person
		return true;
	}
}
