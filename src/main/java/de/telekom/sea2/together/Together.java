package de.telekom.sea2.together;

public class Together<T, U> {
	private T links;
	private U rechts;
	
	public Together() {
	}

	public Together(T links, U rechts) {

		this.links = links;
		this.rechts = rechts;
	}

	public boolean join(T links, U rechts) {
		if (links != null && rechts != null) {
			this.links = links;
			this.rechts = rechts;
			return true;
		} else
			return false;
	}

	
	public T getlinks() {
		return links;
	}
	
	public void setlinks(T links) {
		this.links=links;
	}

	public U getrechts() {
		return rechts;
	}
	
	public void setrechts(U rechts) {
		this.rechts=rechts;
	}
	
	public void split() {
        this.links=null;
        this.rechts=null;
	}

	public String toString() {
		return String.format("%s + %s",this.links.toString(), this.rechts.toString()); 
	}
	
	public int hashCode() {
		return this.links.hashCode() + this.rechts.hashCode();
	}
	
}
