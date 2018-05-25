package br.com.awesome.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.awesome.model.Student;

@RestController
@RequestMapping("student")
public class StudentEndpoint {

	@RequestMapping(method = RequestMethod.GET, path = "/list")
	public List<Student> listAll(){
		ArrayList<Student> lista = new ArrayList<>();
		
		lista.add(new Student("Estudante1"));
		lista.add(new Student("Estudante2"));
		
		return lista;
	}
}
