package com.musterdekho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musterdekho.model.CurrentUserSession;


@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Long>{
	
	public List<CurrentUserSession> findByUuid(String uuid);
	
}
