package br.eti.davinunes.apiconselho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ApiConselhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConselhoApplication.class, args);
	}

}

@RestController
class HelloController {

	@GetMapping("/hello")
	public String hello() {
	    return "Hello, World!";
	}
}
