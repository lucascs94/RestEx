package br.com.demo.javaclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.demo.model.Student;

public class SpringClientTest {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8080/v1/protected/students")
				.basicAuthorization("lucas", "teste").build();

		Student student = restTemplate.getForObject("/{id}", Student.class, 17);
		ResponseEntity<Student> response = restTemplate.getForEntity("/{id}", Student.class, 10);
		Student[] students = restTemplate.getForObject("/", Student[].class);
		ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Student>>() {
				});

		System.out.println(student);
		System.out.println(response.getBody());
		System.out.println(Arrays.toString(students));
		System.out.println(exchange.getBody());
	}

}
