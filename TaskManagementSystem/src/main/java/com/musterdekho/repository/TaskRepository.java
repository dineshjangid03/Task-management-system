package com.musterdekho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musterdekho.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByTitleContainingIgnoreCase(String title);
	
	List<Task> findByDescriptionContainingIgnoreCase(String description);

}
