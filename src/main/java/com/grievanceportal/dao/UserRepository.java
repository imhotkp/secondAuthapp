package com.grievanceportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.grievanceportal.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	@Query("select u from User u where u.email=:email")
	public User getUserbyUserName(@Param("email")String email);

}
