package com.grievanceportal.entity;

import jakarta.persistence.Column;
//
import jakarta.persistence.Entity;

//import javax.persistence.Column;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Complain {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int cId;
private String firstname;
private String secondName;
private String work;
private String email;
private String phone;
private String image;
@ManyToOne
private User user;
@Column(length=500)
private String description;
public int getcId() {
	return cId;
}
public void setcId(int cId) {
	this.cId = cId;
}
public String getFirstName() {
	return firstname;
}
public void setFirstName(String firstname) {
	this.firstname = firstname;
}
public String getSecondName() {
	return secondName;
}
public void setSecondName(String secondName) {
	this.secondName = secondName;
}
public String getWork() {
	return work;
}
public void setWork(String work) {
	this.work = work;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
//@Override
//public String toString() {
//	return "Complain [cId=" + cId + ", firstname=" + firstname + ", secondName=" + secondName + ", work=" + work
//			+ ", email=" + email + ", phone=" + phone + ", image=" + image + ", user=" + user + ", description="
//			+ description + "]";
//}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public boolean equals(Object obj) {
	return this.cId==((Complain)obj).getcId();
}
}
