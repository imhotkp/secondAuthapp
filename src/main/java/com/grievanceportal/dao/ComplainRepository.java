package com.grievanceportal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grievanceportal.entity.Complain;

public interface ComplainRepository extends JpaRepository<Complain, Integer>{
    
	@Query("from Complain as c where c.user.id=:userId")
	public Page<Complain> findComplainbyuser(@Param("userId")int userId,Pageable pageable);
}
