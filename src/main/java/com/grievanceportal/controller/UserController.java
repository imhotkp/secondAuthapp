package com.grievanceportal.controller;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grievanceportal.dao.ComplainRepository;
import com.grievanceportal.dao.UserRepository;
import com.grievanceportal.entity.Complain;
import com.grievanceportal.entity.User;
import com.grievanceportal.helper.Message;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ComplainRepository complainRepository;
	
	//method for adding common data
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
		String userName=principal.getName();
		System.out.println("UserName    "+userName);
		User userbyUserName = userRepository.getUserbyUserName(userName);
		System.out.println(userbyUserName);
		model.addAttribute("user", userbyUserName);	
	}
	//Home page for dashboard
	@GetMapping("/index")
	public String dashboard(Model model,Principal principal) {
		model.addAttribute("title", "User dashboard");
		return "normal/user_dashboard";
	}
	//add form
	@GetMapping("/add-complain")
	public String openAddComplainform(Model model) {
		model.addAttribute("title", "Add Complain");
		model.addAttribute("complain", new Complain());
		return "normal/add_complain_form";}
	
//process Add Form
	@PostMapping("/process-complain")
	public String ProcessComplainform(@ModelAttribute Complain complain,
			@RequestParam("profileImage")MultipartFile file,
			Principal principal,HttpSession session) {
		try {
			String name = principal.getName();
			User user= userRepository.getUserbyUserName(name);
//			processing and uploading file
			if(file.isEmpty()) {
				complain.setImage("complain.jpg");
			}else {complain.setImage(file.getOriginalFilename());
			File savefile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(savefile.getAbsolutePath()+File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("image uploaded");
			}
			complain.setUser(user);
			user.getComplains().add(complain);
			this.userRepository.save(user);
			System.out.println(complain);
			
//			message
			session.setAttribute("message",new Message("your complain is registered now we will take it from here","alert-primary"));
			
		} catch (Exception e) {
			System.out.println("Error" +e.getMessage());
			session.setAttribute("message",new Message("Something Went wrong try again!!!","alert-danger"));
		}
		
		return "normal/add_complain_form";}
	
//	show handler
//	per page 5
//	current page 0
	
	@GetMapping("/show-complain/{page}")
	public String showComplains(@PathVariable("page")Integer page,Model model,Principal principal) {
		model.addAttribute("title", "Show Complain");
		String userName=principal.getName();
		System.out.println("UserName    "+userName);
		User user = userRepository.getUserbyUserName(userName);
		PageRequest pageable = PageRequest.of(page, 5);
		Page<Complain> complainbyuser =  this.complainRepository.findComplainbyuser(user.getId(),pageable);
		model.addAttribute("complain",complainbyuser);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages", complainbyuser.getTotalPages());
		return "normal/show_complain";
	}
@GetMapping("/{cId}/complain")	
public String showComplainDetails(@PathVariable("cId")Integer cId,Model model,Principal principal) {
	Optional<Complain> findById = this.complainRepository.findById(cId);
	Complain complain = findById.get();
	String userEmail = principal.getName();
	User user = this.userRepository.getUserbyUserName(userEmail);
	if(user.getId()==complain.getUser().getId()) {
		model.addAttribute("complain", complain);
	}
	
	return "normal/complain_detail";
}

//handler for delete button

@GetMapping("/delete/{cid}")
public String DeleteComplain(@PathVariable("cid")Integer cId,Model model,HttpSession session,Principal principal) {
	Optional<Complain> complainOptional = this.complainRepository.findById(cId);
	Complain complain = complainOptional.get();
//	complain.setUser(null);
//	this.complainRepository.delete(complain);
	User user = this.userRepository.getUserbyUserName(principal.getName());
	user.getComplains().remove(complain);
	this.userRepository.save(user);
	session.setAttribute("message", new Message("Contact sucessfully completed","alert-primary"));
	return "redirect:/user/show-complain/0";
} 

//handler for update button
@PostMapping("/update-complain/{cid}")
public String updateForm(@PathVariable("cid")Integer cid,Model model) {
	model.addAttribute("title", "Update Complain");
	Complain complain = this.complainRepository.findById(cid).get();
	
	model.addAttribute("complain", complain);
	return "normal/update_form";
	
}
@PostMapping("/process-update") 
public String updateHandler(@ModelAttribute Complain complain,
		@RequestParam("profileImage")MultipartFile file,
		HttpSession session,
		Model model,
		Principal principal) {
	try {
	Complain complain2 = this.complainRepository.findById(complain.getcId()).get();	
	if(!file.isEmpty()) {
		//deleting old file
		File deletefile = new ClassPathResource("static/images").getFile();
		File file2 = new File(deletefile, complain2.getImage());
		file2.delete();
		//adding new file
		File savefile = new ClassPathResource("static/images").getFile();
		Path path = Paths.get(savefile.getAbsolutePath()+File.separator + file.getOriginalFilename());
		Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
		complain.setImage(file.getOriginalFilename());
	}else {
		complain.setImage(complain2.getImage());
	}
	User user = this.userRepository.getUserbyUserName(principal.getName());
	complain.setUser(user);
	this.complainRepository.save(complain);
	}catch (Exception e) {
	e.printStackTrace();
	}
	return "redirect:/user/show-complain/0";}

//profile handler
@GetMapping("/profile")
public String yourProfile(Model m) {
	m.addAttribute("title", "Profile");
	return "normal/profile";}

}
