package com.onlinetutorialspoint.conroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Enabling CrossOrigin at Class Level
//@CrossOrigin(origins = "*")
public class HelloController {

	@GetMapping("/greet")
	// Enabling CrossOrigin at method Level
	@CrossOrigin(origins = "*")
	public String greet() {
		return "Hello From Spring CORS  Resource";
	}
}
