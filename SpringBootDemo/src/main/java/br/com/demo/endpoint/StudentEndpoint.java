package br.com.demo.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.error.CustomErrorType;
import br.com.demo.model.Student;
import br.com.demo.repository.StudentRepository;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

	private final StudentRepository studentDAO;

	@Autowired
	public StudentEndpoint(StudentRepository studentDAO) {
		this.studentDAO = studentDAO;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		Optional<Student> s = studentDAO.findById(id);
		if (!s.isPresent()) {
			return new ResponseEntity<>(new CustomErrorType("Student not found."), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Student student) {
		return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		studentDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student) {
		studentDAO.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
