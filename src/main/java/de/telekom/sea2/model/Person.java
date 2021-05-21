package de.telekom.sea2.model;


import de.telekom.sea2.lookup.*;
import de.telekom.sea2.seminar.*;

/**
 * 
 * @author Michael Altoe
 *
 */
public class Person extends BaseObject{

	private String vorname;
	private String nachname;
	private Long kundennummer;
	private Salutation anrede;
	
	/**
	 * Konstruktor ohne Parameter 
	 */
	public Person() {                                   // Konstruktor ohne Überabeparameter
    }
	
	/**
	 * Konstruktor mit Übergabeparameter der Person
	 * @param vorname - Vorname
	 * @param nachname - Nachname
	 * @param anrede - Anrede
	 */
	public Person(String vorname, String nachname, Salutation anrede) {    // Konstruktor mit Überabeparameter
		this.vorname = vorname;
		this.nachname = nachname;
		this.kundennummer= getId();
		this.anrede=anrede;
	}
	
	/**
	 * Konstruktor mit Übergabeparameter der Person inklusive der Kundennummer (ID)
	 * @param vorname - Vorname
	 * @param nachname  - Nachname
	 * @param kdnr - Kundennummer
	 * @param anrede - Anrede
	 */
	public Person(String vorname, String nachname, long kdnr, Salutation anrede) {    // Konstruktor mit Überabeparameter
		this.vorname = vorname;
		this.nachname = nachname;
		this.kundennummer= kdnr;
//		setId(kdnr+1);
		this.anrede=anrede;
	}
	
	
	/**
	 * Getter 
	 * @return - Anrede
	 */
	public Salutation getAnrede() {
		return anrede;
	}

	/**
	 * Setter
	 * @param anrede - Anrede
	 */
	public void setAnrede(Salutation anrede) {
		this.anrede = anrede;
	}
	
	/**
	 * Setter 
	 * @param b - in DB ist die Anrede als Byte abgelegt
	 */
	public void setAnrede(byte b) {
		this.anrede = Salutation.fromByte(b);
	}
	
	/**
	 * Setter
	 * @param vorname - Vorname
	 */
	public void setVorname (String vorname) {
        this.vorname= vorname;
    }
	   
	/**
	 * Setter 
	 * @param nachname - Nachname
	 */
	public void setNachname (String nachname) {
        this.nachname= nachname;
    }
	
	/**
	 * Setter
	 * @param kdnr Kundennummer (ID)
	 */
	public void setKundennummer (long kdnr) {
        this.kundennummer=kdnr;
    }
	
	/**
	 * Setter ohne Parameter
	 */
	public void setKundennummer () {
        this.kundennummer=getId();
    }
	
	/**
	 * Getter
	 * @return - Vorname
	 */
	public String getVorname () {
        return this.vorname;
    }
	   
	/**
	 * Getter
	 * @return - Nachname
	 */
	public String getNachname () {
        return this.nachname;
    }
			
	/**
	 * Getter
	 * @return - Kundennummer (ID) 
	 */
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
