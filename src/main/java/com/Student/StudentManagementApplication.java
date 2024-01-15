package com.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Student.entity.Student;
import com.Student.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;	
	@Override
	public void run(String... args) throws Exception {
//		
//		Student student1=new Student("Ramesh","Fadatare","juned111alam@gmail.com");
//		studentRepository.save(student1);
//		Student student2=new Student("Asgar","Alam","asgar@gmail.com");
//		studentRepository.save(student2);
//		Student student3=new Student("Juned","Alam","juned@gmail.com");
//		studentRepository.save(student3);
//		
		
		
	}

}
