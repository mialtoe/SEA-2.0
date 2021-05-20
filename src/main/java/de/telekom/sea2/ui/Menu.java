package de.telekom.sea2.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import de.telekom.sea2.model.*;
import de.telekom.sea2.persistance.*;
import de.telekom.sea2.lookup.*;
import de.telekom.sea2.*;
import de.telekom.sea2.seminar.*;
import java.sql.SQLException;


//public class Menu implements MyMenu, EventListener{
public class Menu extends BaseObject {

	
	
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	private String result;
	private PersonRepository pRepo;
	
	public Menu(PersonRepository pRepo) {         // neuer Konstruktor mit Übergabe
		this.pRepo  = pRepo;
	}
		
	
//	public void setMyList(MyList myList) {   // alter Konstruktor
//		this.myList = myList;
//	}

	public void keepAsking() throws IOException,Exception {
		
		do {
			showMenu();
			result = inputMenu();
			try {
				checkMenu(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (!result.equals("q"));
		scanner.close();
	}

	private void showMenu() {
		// anlegen, ausgeben, löschen, ändern?
		System.out.println("********************************");
		System.out.println("* Menü:                        *");
		System.out.println("* 1 - Person anlegen           *");
		System.out.println("* 2 - Liste anzeigen           *");
//		System.out.println("* 3 - Liste löschen            *");
		System.out.println("* 4 - einzelne Person löschen  *");
		System.out.println("* 5 - Person ändern            *");
//		System.out.println("* 5 - Person suchen            *");
//		System.out.println("* 6 - Testdaten eintragen      *");
//		System.out.println("* 7 - Liste aus Datei lesen    *");
//		System.out.println("* 8 - Liste in Datei schreiben *");
		System.out.println("* q - Beenden                  *");
		System.out.println("********************************");
	}

	private String inputMenu() {
		return scanner.nextLine();
	}

	private void checkMenu(String eingabe) throws IOException, Exception, SQLException {
		switch (eingabe) {
		case "1":
			   System.out.println("1: Person anlegen");
			   inputPerson();
			break;
		case "2":
			System.out.println("2: Liste anzeigen");
			listAllPerson();
			break;
		case "4":
			System.out.println("4: Person löschen");
			listAllPerson();
			removePerson();
			break;
		case "5":
			System.out.println("5: bestehende Einträge ändern");
			updatePerson();
			break;
		case "q":
			System.out.println("Beendet");
			break;
		default:
			System.out.println("falsche Eingabe");
		}
	}

	
	private void removePerson() throws SQLException {
		System.out.print("Bitte zu löschende ID eingeben: ");
		int id = scanner.nextInt();
				
		Boolean success = pRepo.delete(id);
		
		if (success) {
			System.out.println("Löschen erfolgreich");
		} else {
			System.out.println("Löschen nicht erfolgreich");
		}
		scanner.nextLine();
	}

	private void inputPerson() {
		// Personendaten einlesen
		String vorname;
		String nachname;
		Salutation anrede;
		int maxId=0;
		
		System.out.print("Bitte Anrede eingeben: ");
		try {
		  	  anrede = Salutation.fromString(scanner.nextLine());
		} catch (IllegalArgumentException e){
			System.out.println("Falsche Eingabe, nur Frau/Herr/divers erlaubt");
			return;
		}
		
		System.out.print("Bitte Vorname eingeben: ");
		vorname = scanner.nextLine();
		System.out.print("Bitte Nachname eingeben: ");
		nachname = scanner.nextLine();
		
		
		try {
		  maxId = pRepo.getMaxId();
		  
		} catch (SQLException e) {e.printStackTrace();}
		
		Person p = new Person(vorname,nachname,maxId+1,anrede);
		try {
		  pRepo.create(p);
		  
		} catch (SQLException e) {e.printStackTrace();}
		
	}

	
		
	private void listAllPerson() throws Exception {
	   
	    
	   Person[] pListe = pRepo.getAll();
	   
	   
	   System.out.println("Aktuelle Komplettliste in DB");
	   for (int i=0; i < pListe.length; i++) {
		   try {
				Person p = (Person) pListe[i];   
				System.out.println(i + ".: " + p.getAnrede() + " " + p.getVorname() + " " + p.getNachname()+" Kundennummer: "+p.getKundennummer());
			}
			catch (Exception re) {   
			   System.out.println("kein gültiger Eintrag gefunden");
			}
	   }
	}

//	private void searchPerson() {
//		String suchString;
//		
//		System.out.print("Bitte Suchstring (Vorname) eingeben: ");
//		suchString = scanner.nextLine();
//		MyList suchList=tList.search(suchString);
//		System.out.println("Liste der gesuchten Personen mit beginendem Vornamen: "+suchString);
//		listAllPerson(suchList); 
//	}
	
	private void updatePerson() throws SQLException {
		String vorname;
		String nachname;
		Salutation anrede;
		
		System.out.print("Bitte ID der zu ändernden Person eingeben: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Bitte Anrede eingeben: ");
		try {
		  	  anrede = Salutation.fromString(scanner.nextLine());
		} catch (IllegalArgumentException e){
			System.out.println("Falsche Eingabe, nur Frau/Herr/divers erlaubt");
			return;
		}
		System.out.print("Bitte neuen Vorname eingeben: ");
		vorname = scanner.nextLine();
		System.out.print("Bitte neuen Nachnamen eingeben: ");
		nachname = scanner.nextLine();
		
		Person p = new Person(vorname,nachname,id,anrede);
				
		Boolean success = pRepo.update(p);
	}
				
//	@Override
//	public void close() {
//		System.out.println("Close durch try with resources");
//		scanner.close();
//	}
	

}
