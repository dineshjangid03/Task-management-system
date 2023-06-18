package com.musterdekho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musterdekho.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
