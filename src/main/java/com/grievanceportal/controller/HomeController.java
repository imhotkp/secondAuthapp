package com.grievanceportal.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grievanceportal.dao.UserRepository;
import com.grievanceportal.entity.User;
import com.grievanceportal.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;	
	
@Autowired
private UserRepository userRepository;

//@GetMapping("/test")
//@ResponseBody
//public String test() {
//	User user = new User();
//	user.setName("Kaushlendra");
//	user.setEmail("main@gmail.io");
//	userRepository.save(user);
//	return "Working";
//	
//}
@GetMapping("/")	
public String home(Model m) {
	m.addAttribute("title", "Home-Grievance Manager");
	return "home";
}
@GetMapping("/about")	
public String about(Model m) {
	m.addAttribute("title", "about-Grievance Manager");
	return "about";
}
@GetMapping("/contact")	
public String contact(Model m) {
	m.addAttribute("title", "contact-Grievance Manager");
	return "contact";
}
@GetMapping("/signup")	
public String register(Model m) {
	m.addAttribute("title", "Register-Grievance Manager");
	m.addAttribute("user",new User());
	return "signup";
}
@PostMapping("/register")	
public String registering(@ModelAttribute("user")User user,
		@RequestParam(value="agreement",defaultValue="false")boolean agreement,@RequestParam("userImage")MultipartFile file,
		Model model,HttpSession session) {
	try {
		if(!agreement) {
			System.out.println("You should check Agree");
			throw new Exception("You should check Agree");
		}
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		//user.setImageUrl("default.png");
		
		
//		image uploading begins
		if(file.isEmpty()) {
			user.setImageUrl("Default.jpg");
		}else {user.setImageUrl(file.getOriginalFilename());
		File savefile = new ClassPathResource("static/images").getFile();
		Path path = Paths.get(savefile.getAbsolutePath()+File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		System.out.println("image uploaded");
		}
		
		
		
//		image uploading ends
		
		
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println("Agreement "+agreement);
		System.out.println("User"+user);
		User result = this.userRepository.save(user);
		model.addAttribute("user", new User());
	
		session.setAttribute("message", new Message("Registered Sucessfully","alert-primary"));
		return "signup";
		
	} catch (Exception e) {
		e.printStackTrace();
		model.addAttribute("user", user);
		session.setAttribute("message", new Message("something went wrong"+e.getMessage(),"alert-danger"));
		return "signup";
	}
//	if(!agreement) {
//		System.out.println("You should check Agree");
//	}
//	user.setRole("Role User");
//	user.setEnabled(true);
//	System.out.println("Agreement "+agreement);
//	System.out.println("User"+user);
//	User result = this.userRepository.save(user);
//	model.addAttribute("user", result);
	
	
}
@GetMapping("/signin")
public String customLogin(Model model) {
	model.addAttribute("title", "Login-Grievance Manager");
	return "login";
}

}
