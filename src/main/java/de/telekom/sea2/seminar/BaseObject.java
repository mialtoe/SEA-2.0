package de.telekom.sea2.seminar;

public class BaseObject {

	private Object parent;
	private long id;
	private static long idCounter = 1;
	
	public BaseObject() {  // Konstruktor
		this.id = idCounter++;	
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long count) {
		idCounter=count;
	}

	@Override
	public boolean equals(Object obj) {
//		if (obj == this) {   // selbe Instanz?         
//			return true;
//		}
//			                    die beiden Abfragen werden durch super Klasse ersetzt
//		if (obj == null) {    
//			return false;
//		}
			
		if (!super.equals(obj)) {
			return false;
		}
		
        if (!(obj instanceof BaseObject) ) {        // ist obj vom Typ BaseObject?
		    return false;
        }
		
		BaseObject otherBaseObject = (BaseObject) obj;
		return (this.id == otherBaseObject.id);       // ist die Id gleich?
    }
	
	@Override
	public String toString() {
	     Long l = id;
	     return l.toString();
	}
	
	
	public Object getParent() {
	    return parent;
    }

	public void setParent(Object parent) {
	    this.parent = parent;
	    // System.out.println(this.parent);
	}
	
}
