package de.telekom.sea2.model;

//import de.telekom.sea2.*;
import de.telekom.sea2.lookup.*;
import de.telekom.sea2.seminar.*;

public class Person extends BaseObject{

	private String vorname;
	private String nachname;
	private Long kundennummer;
	private Salutation anrede;
	
	 
	public Person() {                                   // Konstruktor ohne Überabeparameter
    }
	
	public Person(String vorname, String nachname, Salutation anrede) {    // Konstruktor mit Überabeparameter, verwendet in inputPerson
		this.vorname = vorname;
		this.nachname = nachname;
		this.kundennummer= getId();
		this.anrede=anrede;
	}
	
	public Person(String vorname, String nachname, long kdnr, Salutation anrede) {    // Konstruktor mit Überabeparameter
		this.vorname = vorname;
		this.nachname = nachname;
		this.kundennummer= kdnr;
		setId(kdnr+1);
		this.anrede=anrede;
	}
	
	public Salutation getAnrede() {
		return anrede;
	}

	public void setAnrede(Salutation anrede) {
		this.anrede = anrede;
	}
	
	public void setAnrede(byte b) {
		this.anrede = Salutation.fromByte(b);
	}
	
	public void setVorname (String vorname) {
        this.vorname= vorname;
    }
	   
	public void setNachname (String nachname) {
        this.nachname= nachname;
    }
	
	public void setKundennummer (long kdnr) {
        this.kundennummer=kdnr;
    }
	
	public void setKundennummer () {
        this.kundennummer=getId();
    }
	
	public String getVorname () {
        return this.vorname;
    }
	   
	public String getNachname () {
        return this.nachname;
    }
			
	public Long getKundennummer () {
        return this.kundennummer;
    }
	
	@Override
	public boolean equals(Object p1) {
//		  if (p1 == null) {
//			  System.out.println("ist NULL");
//			  return false;
//		  }
//		  if (this  == p1) {
//			  System.out.println("dasselbe Objekt");
//			  return true;
//		  }
		  
		  
		  //System.out.println(this.getVorname());
		  //System.out.println(p1.getVorname());
          
		  Person  person= (Person) p1;
		  if (!super.equals(p1)) {                  // ruft die übergeordnete Klasse auf
			  return false;
		  } 

		  if (!(p1 instanceof Person) ) {        // ist obj vom Typ Person?
				 return false;
		      }
		  		  
		  if ( ( this.getVorname() == person.getVorname() ) && (this.getNachname() == person.getNachname() ) ) {
			  System.out.println("Person ist gleich");
		      return true;
		  }
		  else {
			  System.out.println("Person ist ungleich");
			  return false;
		  }
	}

}
