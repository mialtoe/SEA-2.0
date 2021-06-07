package de.telekom.sea2.model;

//import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

	private Person cut; // cut = class under test

	@BeforeEach
	void setup() {
		cut = new Person();
	}

	@Test
	void setFirstname_test() {
		cut.setVorname("Sarah");
		
		var result = cut.getVorname();
		
		assertEquals("Sarah", result);
//        	fail();
	}
	
	@Test
	void setNachname_test() {
		cut.setNachname("Paulsen");
		
		var result = cut.getNachname();
		
		assertEquals("Paulsen", result);
	}
	

	@AfterEach
	void teardown() {
		cut = null;
	}

}
