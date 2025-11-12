package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.StudentPojo;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentDao studentDao;

	public StudentController(StudentDao studentDao) {
	        this.studentDao = studentDao;
	    }

	@PostMapping()
	public StudentPojo addStudentDetails(@RequestBody StudentPojo studentdetails) throws ClassNotFoundException {
		StudentPojo details = null;
		int result = studentDao.addStudentDetails(studentdetails);
		if (result == 1) {
			details = studentDao.getStudentDetailsbyId(studentdetails.getStudentId());
		}
		return details;

	}
	
	@PutMapping("/edit")
	public StudentPojo editStudentDetails(@RequestBody StudentPojo studentdetails) throws ClassNotFoundException {
		StudentPojo details = null;
		int result = studentDao.editStudentDetails(studentdetails);
		if (result == 1) {
			details = studentDao.getStudentDetailsbyId(studentdetails.getStudentId());
		}
		return details;
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudentDetails(@PathVariable("id") int id) {
		String message= "Record not Found";
		int result = studentDao.deleteStudentDetails(id);
		if (result==1){
			message= "Record Deleted";
		}
		return message;

	}
	
	@GetMapping("/{id}")
	public StudentPojo getStudentDetails(@PathVariable("id") int id) {
		StudentPojo details = new StudentPojo();
		try {
			details = studentDao.getStudentDetailsbyId(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return details;
	}
	
	@GetMapping()
	public List<StudentPojo> getAllStudentDetails() {
		return studentDao.getAllStudentDetails();
	}

}
