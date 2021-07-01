package de.telekom.sea2.ui;

import java.io.IOException;
import de.telekom.sea2.model.*;
import de.telekom.sea2.persistance.*;
import de.telekom.sea2.lookup.*;
import de.telekom.sea2.seminar.*;
import java.sql.SQLException;
import java.util.ArrayList;



public class Menu extends BaseObject {

	
	
	private java.util.Scanner scanner = new java.util.Scanner(System.in);
	private String result;
	private PersonRepository pRepo;
	
	public Menu(PersonRepository pRepo) {         // neuer Konstruktor mit Übergabe
		this.pRepo  = pRepo;
	}
	

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
		System.out.println("* 6 - Anzahl Personen in DB    *");
		System.out.println("* 7 - Person suchen            *");
		System.out.println("* q - Beenden                  *");
		System.out.println("********************************");
	}

	private void showAenderungsMenu() {
		// was soll geändert werden?
			
		System.out.println("********************************");
		System.out.println("* Änderungs-Menü:              *");
		System.out.println("* Was soll geändert werden?    *");
		System.out.println("* 1 - Anrede                   *");
		System.out.println("* 2 - Vorname                  *");
		System.out.println("* 3 - Nachname                 *");
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
		case "6":
			System.out.println("6: Anzahl Einträge in DB");
			int anz = pRepo.getCountDB();
			if (anz > 0)
				System.out.println(String.format("In der DB befinden sich %d Einträge",anz));
			else 
				System.out.println("Keine Einträge in DB gefunden");
			break;
		case "7":
			System.out.println("5: Person suchen");
			searchPerson();
			break;
		case "q":
			System.out.println("Beendet");
			break;
		default:
			System.out.println("falsche Eingabe");
		}
	}

	private String checkAenderungsMenu(String eingabe) throws IOException, Exception, SQLException {
		String rc;
		switch (eingabe) {
		case "1":
			   System.out.println("neue Anrede eingeben: ");
			   rc=inputMenu();
 			   break;
		case "2":
			   System.out.println("neuen Vornamen eingeben: ");
			   rc=inputMenu();
			   break;
		case "3":
			   System.out.println("neuen Nachnamen eingeben: ");
			   rc=inputMenu();
			   break;
		default:
			   System.out.println("falsche Eingabe");
			   rc="";
		}
		return rc;
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
	   
	    
	   ArrayList<Person> pListe = pRepo.getAll();
	   
	   
	   System.out.println("Aktuelle Komplettliste in DB");
	   for (int i=0; i < pListe.size(); i++) {
		   try {
				Person p = (Person) pListe.get(i);   
				System.out.println(String.format("%d %s %s %s  Kundennummer: %d",i,p.getAnrede(), p.getVorname(),p.getNachname(),p.getKundennummer()));
			}
			catch (Exception re) {   
			   System.out.println("kein gültiger Eintrag gefunden");
			}
	   }
	}

	private void searchPerson() throws Exception {
		String suchString;
		
		System.out.print("Bitte Suchstring (Teil des Vor- oder Nachnamen) eingeben: ");
		suchString = scanner.nextLine();
		
		Person[] pListe = pRepo.searchP(suchString);
		if (pListe.length == 0)
			System.out.println(String.format("kein Eintrag mit %s gefunden!",suchString));
		
		for (int i=0; i < pListe.length; i++) {
			   try {
					Person p = (Person) pListe[i];   
					System.out.println(String.format("%d %s %s %s  Kundennummer: %d",i,p.getAnrede(), p.getVorname(),p.getNachname(),p.getKundennummer()));
//					System.out.println(i + ".: " + p.getAnrede() + " " + p.getVorname() + " " + p.getNachname()+" Kundennummer: "+p.getKundennummer());
				}
				catch (Exception re) {   
				   System.out.println("kein gültiger Eintrag gefunden");
				}
		}
	}
	
	private void updatePerson() throws Exception {
//		Salutation anrede;
		
		System.out.print("Bitte ID der zu ändernden Person eingeben: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		Person p = pRepo.get(id);
		if (p.getKundennummer() != null) {
		
//   		   anrede = p.getAnrede();
		   System.out.println(String.format("Zu ändernder Datensatz: %d %s %s %s",p.getKundennummer(),p.getAnrede(), p.getVorname(),p.getNachname()));
//   	   System.out.println("Zu ändernder Datensatz: ID "+ p.getKundennummer()+" "+anrede+" "+p.getVorname()+" "+p.getNachname());
		   showAenderungsMenu();
		
		   result = inputMenu();
		   try {
			   String aenderung = checkAenderungsMenu(result);
			   System.out.println(String.format("geänderter Text: %s",aenderung));
			   switch (result) {
			   case "1":
				   p.setAnrede(Salutation.fromString(aenderung));
				   break;
			   case "2":
				  p.setVorname(aenderung);
				  break;
		   	   case "3":
				  p.setNachname(aenderung);
				  break;
		       default:
		       }
			   	
			   pRepo.update(p);
		    	  
		   } catch (IOException e) {
			   
			   e.printStackTrace();}
		 } else 
			 System.out.println("ID nicht gefunden!");
	}

				
//	@Override
//	public void close() {
//		System.out.println("Close durch try with resources");
//		scanner.close();
//	}
	

}
