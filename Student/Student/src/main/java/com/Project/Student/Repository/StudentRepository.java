package com.Project.Student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Student.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>
{
	boolean existsByEmail(String email);
	
	Student findByEmailAndPassword(String email,String password);

}
