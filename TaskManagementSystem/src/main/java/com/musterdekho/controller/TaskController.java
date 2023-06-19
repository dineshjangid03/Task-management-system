package com.musterdekho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;
import com.musterdekho.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<String>("Welcome",HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) throws UserNotFoundException {
        return new ResponseEntity<Task>(taskService.createTask(userId, task), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask) throws TaskNotFoundException {
    	return new ResponseEntity<Task>(taskService.updateTask(updatedTask), HttpStatus.OK);
    }
    
    @PutMapping("/{taskId}/{userId}")
    public ResponseEntity<Task> assignTaskToAnotherUser(@PathVariable Long taskId, @PathVariable Long userId) throws TaskNotFoundException, UserNotFoundException {
    	return new ResponseEntity<Task>(taskService.assignTaskToAnotherUser(taskId, userId), HttpStatus.OK);
    }

    @PutMapping("/complete/{taskId}")
    public ResponseEntity<Task> markTaskComplete(@PathVariable Long taskId) throws TaskNotFoundException {
    	return new ResponseEntity<Task>(taskService.markTaskComplete(taskId), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
    	return new ResponseEntity<Task>(taskService.deleteTask(taskId), HttpStatus.OK);
    }

}
