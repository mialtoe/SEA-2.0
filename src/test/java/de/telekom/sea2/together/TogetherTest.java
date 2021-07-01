package de.telekom.sea2.together;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTimeout;
//import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static java.time.Duration.ofMillis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TogetherTest {
    
	private Together <String, String> cut;
	
	@BeforeEach
	void setup() {
		cut = new Together<String, String> ("Hans","Meiser");
	}
	
	@Test
	void joinTest() {
		//Arrange
		Together<String, String> together = new Together<String,String> ();
		//Act
//		together.setlinks("Hans");
//		together.setrechts("Meiser");
		together.join("Hans", "Meiser");
		
		//Assert
		assertEquals("Hans", together.getlinks());
		assertEquals("Meiser", together.getrechts());
	}
	
	@Test
	void splitTest() {
		//Arrange

		//Act
		cut.split();
		
		//Assert
		assertNull(cut.getlinks());
		assertNull(cut.getrechts());
	}
		
	@Test
	void hashCodeTest() {
		//Arrange
		Together<String, String> vergleich= new Together<String, String>("Hans", "Hans");
		
		//Act
		
		//Assert
		assertNotEquals(cut.hashCode(),vergleich.hashCode());
	}
	
	@AfterEach
	void teardown() {
		cut = null;
	}
	
	
}
