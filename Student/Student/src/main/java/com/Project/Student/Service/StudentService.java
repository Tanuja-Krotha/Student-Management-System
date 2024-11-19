package com.Project.Student.Service;

import java.util.List;

import com.Project.Student.Model.Student;
public interface StudentService 
{
	public Student createStudent(Student student);

	public List<Student> getAllStudents();

	public Student getStudentById(int id);

	public Student updateStudent(Student student, int id);

	public String DeleteStudent(int id);
	
	boolean existByEmail(String email);
	
	Student findByEmailAndPassword(String email,String password);
}
