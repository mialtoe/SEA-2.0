package de.telekom.sea2.pair;

//import java.lang.Iterable;
import java.util.Iterator;


public class PairIterator<T> implements Iterator<T>{
	   Pair<T> pair;
	   boolean t = true;
	   boolean hasNxt = true;

//	   Konstruktor
	   public PairIterator(Pair<T> pair){
	      this.pair = pair;
	   }
	   
	   @Override
	   public T next(){
	      if (t) {
	        t=false;
	        return pair.getlinks();
	      } else {
	        hasNxt = false;
	        return pair.getrechts();
	      }
	   }
	   
	   @Override
	   public boolean hasNext(){
	     return hasNxt;
	   }
	}