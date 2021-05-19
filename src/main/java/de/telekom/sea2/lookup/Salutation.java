package de.telekom.sea2.lookup;

//import de.telekom.sea2.seminar.*;

public enum Salutation {
	       HERR,
	       FRAU,
	       DIVERS;
		
		 
		public static Salutation fromString(String string) {
			 
			 switch (string.toUpperCase()) {
			 case "FRAU"  : 
			 case "F"     : return FRAU;
			 case "MANN"  :
			 case "HERR"  :
			 case "M"     : return HERR;
			 case "D"     : 
			 case "DIVERS": return DIVERS;
			 default:      throw new IllegalArgumentException("Unexpected value: "+string);
			 }
	     }
		 
		 @Override
		 public String toString() {
			 switch (this) {
			 case FRAU : return "Frau";
			 case HERR : return "Herr";
			 case DIVERS: return "Divers";
		     default:      throw new IllegalArgumentException("Unexpected value:");   // default kann praktisch nie eintreten.
			 }
		 }
	
}
