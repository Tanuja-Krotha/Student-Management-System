package com.Project.Student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Project.Student.Model.Student;
import com.Project.Student.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController 
{
	
	@Autowired
	private StudentService studentService;

    @GetMapping("/page")
    public String showRegisterPage(Model model) 
    {
        model.addAttribute("student", new Student());
        return "index";
    }
        
    @PostMapping("/post")
    public String registerStudent(@ModelAttribute Student student, Model model)
    {
        if (studentService.existByEmail(student.getEmail()))
        {
            model.addAttribute("errorMessage", "Email is already registered");
            return "index";
        }
        studentService.createStudent(student);
        return "login";
    }
    
    
    @GetMapping("/login")
    public String getLoginPage()
    {
    	return "login";
    }
    
    @PostMapping("/login")
    public String loginStudent(@RequestParam String email, @RequestParam String password, HttpSession session, Model model)
    {
        if ("React@gmail.com".equals(email) && "React@123".equals(password)) 
        {
            return "redirect:/getAllStudents";
        }
        Student student = studentService.findByEmailAndPassword(email, password);
        
        if (student!= null) 
        {
            session.setAttribute("studentId", student.getId());
            return "redirect:/getStudentById/" + student.getId();
        } 
        else{
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }
        
    
    @GetMapping("/getStudentById/{id}")
	public String getStudentById(@PathVariable("id") int id, Model model)
	{
	    Student student = studentService.getStudentById(id);
	    if (student != null) 
	    {
	        model.addAttribute("studentData", student);
	        return "ById";
	    }
	    else
	    {
	        model.addAttribute("errorMessage", "No Student found...");
	        return "login"; 
	    }
	}   
	
	@GetMapping("/getAllStudents")
	public String getAllStudents(Model model)
	{
		List <Student> list = studentService.getAllStudents();
		model.addAttribute("list", list);
		return "student";
	}
	
	@GetMapping("/updateStudent/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model)
	{
        Student student = studentService.getStudentById(id);
        if (student != null) 
        {
            model.addAttribute("studentData", student);
            return "update"; 
        } else {
            model.addAttribute("errorMessage", "Student not found");
            return "login"; 
        }
    }
	
	
	@GetMapping("/updateS/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model)
	{
        Student student = studentService.getStudentById(id);
        if (student != null) 
        {
            model.addAttribute("studentData", student);
            return "updateById"; 
        } else {
            model.addAttribute("errorMessage", "Student not found");
            return "login"; 
        }
    }
	
	@PostMapping("/updatedata/{id}")
	public String insertUpdatedData(@ModelAttribute Student student, @PathVariable("id") int id) 
	{		
		studentService.updateStudent(student, id);
		return "redirect:/getAllStudents";
	}
	
	@PostMapping("/updated/{id}")
	public String UpdateById(@ModelAttribute Student student, @PathVariable("id") int id) 
	{		
		studentService.updateStudent(student, id);
		return "redirect:/getStudentById/{id}";
	}
	
	
	
	@PostMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") int id) 
	{
		studentService.DeleteStudent(id);
		return "redirect:/getAllStudents";
	}
}