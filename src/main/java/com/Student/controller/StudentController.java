package com.Student.controller;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Student.entity.Student;
import com.Student.service.StudentService;

@Controller
public class StudentController {

	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	
	//handler method to handle list students
	@GetMapping("/students")
	public String listStudents(Model model) {
		return findPaginated(1,"firstName","asc",model);
		
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		//to hold student form data
		Student student=new Student();
		model.addAttribute("student",student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudents(@ModelAttribute("student") Student student) {
	
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id,Model model) {
		
		model.addAttribute("student",studentService.getStudentById(id));
		
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		
		//get student from database
		Student exisitingStudent=studentService.getStudentById(id);
		exisitingStudent.setId(id);
		exisitingStudent.setFirstName(student.getFirstName());
		exisitingStudent.setLastName(student.getLastName());
		exisitingStudent.setEmail(student.getEmail());
		
		//save updated student object
		studentService.updateStudent(exisitingStudent);
		return "redirect:/students";
		
	}
	
	//to handle delete student method
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value="pageNo" ) int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			
			Model model) {
		
		int pageSize=5;
		
		Page<Student> page=studentService.findPaginated(pageNo,pageSize,sortField,sortDir);
		 List<Student> listStudents = page.getContent();
	model.addAttribute("currentPage", pageNo);
	
	model.addAttribute("totalPages", page.getTotalPages());
	model.addAttribute("totalItems", page.getTotalElements());
	
	model.addAttribute("sortField",sortField);
	model.addAttribute("sortDir",sortDir);
	model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc":"asc");
	
	model.addAttribute("listStudents", listStudents);
	return "students";
	}
	
	
	
}
