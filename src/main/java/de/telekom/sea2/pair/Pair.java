package de.telekom.sea2.pair;

import java.lang.Iterable;
import java.util.Iterator;

import de.telekom.sea2.together.*;

public class Pair<T> extends Together<T,T> implements Iterable<T> {
    
	public Iterator<T> iterator(){
       return new PairIterator<T>(this);
    }
}