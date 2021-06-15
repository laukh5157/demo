package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// add annotation to GreetingController to convert it to a Rest Controller component
@RestController
public class GreetingController {
	private static final String template ="Hello ,%s!";
	private final AtomicLong counter = new AtomicLong();
	
	//define a GreetingComponent variable without initialization
	private GreetingComponent g;
	
	// inject/initialize GreetingComponent in the constructor
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g = g;
	}
	
	// test the greeting component
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(g.getMessage());
	}
	
	@GetMapping("/greeting")
	// value="name" to tell url ,the parameter using 
	// eg. http://www.abc.com/user?name=2
	public Greeting greeting(@RequestParam(value="name",defaultValue="World") String name) {
	 return new Greeting(counter.incrementAndGet(), String.format(template, name));	
	}
	
	@GetMapping("/add/{num1}/{num2}")
	public Integer addTwoNumber(@PathVariable("num1") Integer  num1, @PathVariable("num2") Integer num2) {
		return num1+num2;
	}
	@GetMapping("/math/{char1},{num1},{num2}")
	public float addTwoNumber(@PathVariable("char1") char char1,@PathVariable("num1") Integer  num1, @PathVariable("num2") Integer num2) {
		float value = 0;
		if(char1 == '*'){
			value = num1 * num2;
		}
		if(char1 == '/'){
			value = num1 / num2;
		}
		if(char1 == '+'){
			value = num1 + num2;
		}
		if(char1 == '-'){
			value = num1 - num2;
		}

		return value;
	}
}
