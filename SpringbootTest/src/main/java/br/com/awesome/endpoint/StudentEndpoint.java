package br.com.awesome.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.awesome.error.CustomErrorType;
import br.com.awesome.model.Student;

@RestController
@RequestMapping("students")
public class StudentEndpoint {

	@Autowired
	public StudentEndpoint() {
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
		Student s = new Student();
		s.setId(id);
		int index = Student.studentList.indexOf(s);
		if (index == -1) {
			return new ResponseEntity<>(new CustomErrorType("Student not found."), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Student student) {
		Student.studentList.add(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestBody Student student) {
		Student.studentList.remove(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Student student) {
		Student.studentList.remove(student);
		Student.studentList.add(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}
