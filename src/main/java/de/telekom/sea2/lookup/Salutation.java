package de.telekom.sea2.lookup;

//import de.telekom.sea2.seminar.*;

public enum Salutation {
	       FRAU,
	       HERR,
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
		 
		 public byte toByte() {
			 switch (this) {
			 case HERR:  return 1;
			 case FRAU:  return 2;
			 case DIVERS: return 3;
			 default:      throw new IllegalArgumentException("Unexpected value:");   // default kann praktisch nie eintreten.
			 }
		 }
		 
		 public static Salutation fromByte(Byte b) {
			 switch (b) {
			 case 1:   return HERR;
			 case 2:   return FRAU;
			 case 3:   return DIVERS;
			 default:      throw new IllegalArgumentException("Unexpected value:");   // default kann praktisch nie eintreten.
			 }
		 }
	
}
