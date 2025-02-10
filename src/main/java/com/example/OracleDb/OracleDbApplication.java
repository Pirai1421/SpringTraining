package com.example.OracleDb;

import com.example.OracleDb.Entity.Student;
import com.example.OracleDb.dao.Studentdao;
import com.example.OracleDb.dao.StudentdaoImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OracleDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleDbApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(Studentdao studentdao){
		return runner -> {
			//createStudent(studentdao);
			//readStudent(studentdao);
			//UpdateStudents(studentdao);
			//queryforLastname(studentdao);
			//	queryForStudents(studentdao);
			DeleteStudent(studentdao);
		};
	}

	private void UpdateStudents(Studentdao studentdao) {
//		int updated=studentdao.update();
//		System.out.println(updated);
		int studId=1;
		Student s=studentdao.findbyId(studId);
		s.setFirstname("Pirai");
		studentdao.updates(s);
		System.out.println(s);
	}

	private void DeleteStudent(Studentdao studentdao){
		int id=1;

		studentdao.removes(id);

	}

	private void queryforLastname(Studentdao studentdao) {
		List<Student> theStud=studentdao.findbyLastName("kumar");
		for (Student i: theStud){
			System.out.println(theStud);
		}
	}

	private void queryForStudents(Studentdao studentdao) {
		List<Student> theStudents=studentdao.findall();
		for (Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readStudent(Studentdao studentdao) {
		System.out.println("creating new Student object");

		Student tempStudent= new Student ("yathis","kumar","yathis@gmail.com");
		studentdao.save(tempStudent);
		int theid=tempStudent.getId();
		System.out.println("saved Student");
		Student findStudent= studentdao.findbyId(theid);
		System.out.println(findStudent);
	}

	private void createStudent(Studentdao studentdao) {
		System.out.println("creating a new object...");

		Student tempStudent=new Student("pirai","soodan","pirai@gmail.com");
		studentdao.save(tempStudent);
		System.out.println("Saved Student . Generated id:"+ tempStudent.getId());

	}


}
