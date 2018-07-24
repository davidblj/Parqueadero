package com.david.greet;

import org.junit.*;

public class GreetTests {
	
	 @Test
	  public void helloShouldReturnHelloWithName() {
	    Greet greet = new Greet();
	    Assert.assertEquals("must return hello with name", "Hello David",   greet.hello("David"));
	  }
	 
	  @Test
	  public void byeShouldReturnByeWithName() {
	    Greet greet = new Greet();
	    Assert.assertEquals("must return bye with name", "Bye David", greet.bye("David"));
	  }
}
