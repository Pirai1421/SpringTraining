package com.example.cruddemo;

import com.example.cruddemo.dao.Appdao;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(Appdao appdao){
		return runner ->{
			createInstructor(appdao);
//			findInstructor(appdao);
//			deleteInstructor(appdao);
		};
	}

	private void findInstructor(Appdao appdao) {
		Instructor i=appdao.findInstructorById(2);
		System.out.println(i);
	}


	private void deleteInstructor(Appdao appdao){
		int id=2;
		appdao.deleteInstructorById(2);
	}

	private void createInstructor(Appdao appdao) {
		Instructor tempInstructor=new Instructor("pirai","Soodan","pirai@gmail.com");
		InstructorDetail tempInstDet=new InstructorDetail("mychannel","cricket");
		tempInstructor.setInstructorDetail(tempInstDet);
		appdao.save(tempInstructor);
	}
}
