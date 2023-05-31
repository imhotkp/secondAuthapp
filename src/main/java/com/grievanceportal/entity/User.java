package com.grievanceportal.entity;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//
//
//
//import java.util.ArrayList;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	public List<Complain> getComplains() {
		return complains;
	}
	public void setComplains(List<Complain> complains) {
		this.complains = complains;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String name;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user",orphanRemoval = true)
	private List<Complain>complains=new ArrayList<>();
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", complains=" + complains + ", email=" + email + ", password="
				+ password + ", role=" + role + ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about
				+ "]";
	}
	@Column(unique=true)
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String password;
	private String role;
	private Boolean enabled;
	private String imageUrl;
	private String about;

}
//@Entity
//public class User{
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
//	private int id;
//	private String email;
//	private String name;
//	public int getId() {
//		return id;
//	}
//	public User() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public User(int id, String email, String name) {
//		super();
//		this.id = id;
//		this.email = email;
//		this.name = name;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//}