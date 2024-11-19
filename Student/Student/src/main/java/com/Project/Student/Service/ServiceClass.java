package com.Project.Student.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.Project.Student.Model.Student;
import com.Project.Student.Repository.StudentRepository;

@Service
public class ServiceClass implements StudentService
{	
	@Autowired
	private StudentRepository repo;
	
	@Override
	public Student createStudent(Student student)
	{
		 return repo.save(student);
	}
	
	@Override
	public List<Student> getAllStudents()
	{
		 return repo.findAll();
	}
	
	@Override
	public Student getStudentById(int id)
	{	
		return repo.findById(id).orElse(null);
	}

	@Override
	public Student updateStudent(Student student,int id)
	{
		Student es=repo.findById(id).orElse(student);
		es.setName(student.getName());
		es.setEmail(student.getEmail());
		es.setPassword(student.getPassword());
		
		return repo.save(es);				
	}
	@Override
	public String DeleteStudent(int id) 
	{
		repo.deleteById(id);
		return "Student Deleted Sucessfully";
	}

	@Override
	public boolean existByEmail(String email)
	{
		return repo.existsByEmail(email);
	}

	@Override
	public Student findByEmailAndPassword(String email, String password) 
	{
		
		return repo.findByEmailAndPassword(email, password);
	}
}
