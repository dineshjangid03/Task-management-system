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

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskException;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.exception.UserNotLoggedInException;
import com.musterdekho.model.Task;
import com.musterdekho.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<String>("Welcome",HttpStatus.OK);
    }

    @PostMapping("/{token}")
    public ResponseEntity<TaskDTO> createTask(@PathVariable String token, @RequestBody @Valid Task task) throws UserNotFoundException, UserNotLoggedInException {
        return new ResponseEntity<TaskDTO>(taskService.createTask(token, task), HttpStatus.OK);
    }

    @PutMapping("/{token}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String token, @RequestBody @Valid Task updatedTask) throws TaskNotFoundException, UserNotLoggedInException, TaskException {
    	return new ResponseEntity<TaskDTO>(taskService.updateTask(token, updatedTask), HttpStatus.OK);
    }
    
    @PutMapping("/{token}/{taskId}/{userId}")
    public ResponseEntity<TaskDTO> assignTaskToAnotherUser(@PathVariable String token, @PathVariable Long taskId, @PathVariable Long userId) throws TaskNotFoundException, UserNotFoundException, UserNotLoggedInException, TaskException {
    	return new ResponseEntity<TaskDTO>(taskService.assignTaskToAnotherUser(token, taskId, userId), HttpStatus.OK);
    }

    @PutMapping("/complete/{token}/{taskId}")
    public ResponseEntity<TaskDTO> markTaskComplete(@PathVariable String token, @PathVariable Long taskId) throws TaskNotFoundException, UserNotLoggedInException, TaskException {
    	return new ResponseEntity<TaskDTO>(taskService.markTaskComplete(token, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/{token}/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable String token, @PathVariable Long taskId) throws TaskNotFoundException, UserNotLoggedInException, TaskException {
    	return new ResponseEntity<Task>(taskService.deleteTask(token, taskId), HttpStatus.OK);
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return new ResponseEntity<TaskDTO>(taskService.getTaskById(taskId),HttpStatus.OK);
    }

}
