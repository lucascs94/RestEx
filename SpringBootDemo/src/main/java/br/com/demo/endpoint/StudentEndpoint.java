package br.com.demo.endpoint;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.error.ResourceNotFoundException;
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
	public ResponseEntity<?> listAll(Pageable pageable, Sort sort) {
		return new ResponseEntity<>(studentDAO.findAll(pageable.getSortOr(sort)), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		verifyIfStudentExists(id);
		Optional<Student> s = studentDAO.findById(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping(value = "/findByName/{name}")
	public ResponseEntity<?> getStudentByName(@PathVariable("name") String name) {
		verifyIfStudentExists(name);
		List<Student> s = studentDAO.findByNameIgnoreCaseContaining(name);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> save(@Valid @RequestBody Student student) {
		return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		verifyIfStudentExists(id);
		studentDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student) {
		verifyIfStudentExists(student.getId());
		studentDAO.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void verifyIfStudentExists(Long id) {
		if(!studentDAO.findById(id).isPresent()) {
			throw new ResourceNotFoundException("Student not found for id: " + id + ".");
		}
	}
	
	private void verifyIfStudentExists(String name) {
		if(!studentDAO.findByNameIgnoreCaseContaining(name).isEmpty()) {
			throw new ResourceNotFoundException("Student(s) not found for name: " + name + ".");
		}
	}
}
