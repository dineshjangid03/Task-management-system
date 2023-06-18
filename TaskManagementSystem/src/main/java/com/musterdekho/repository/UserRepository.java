package com.musterdekho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musterdekho.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
