package com.musterdekho.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;
import com.musterdekho.service.SearchAndFilterService;

@RestController
@RequestMapping("/api")
public class SearchAndFilterController{
	
	@Autowired
	private SearchAndFilterService searchAndFilterService;

	@GetMapping("/search/title/{title}")
	public ResponseEntity<List<TaskDTO>> searchTaskByTitle(@PathVariable String title) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskByTitle(title), HttpStatus.OK);
	}

	@GetMapping("/search/desc/{desc}")
	public ResponseEntity<List<TaskDTO>> searchTaskByDescription(@PathVariable String desc) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskByDescription(desc), HttpStatus.OK);
	}

	@GetMapping("/search/user/{userId}")
	public ResponseEntity<List<TaskDTO>> searchTaskOfUser(@PathVariable Long userId) throws TaskNotFoundException, UserNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskOfUser(userId), HttpStatus.OK);
	}

	@GetMapping("/filter/completionstatus/{completedStatus}")
	public ResponseEntity<List<TaskDTO>> filterTaskByCompletionStatus(@PathVariable Boolean completedStatus) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByCompletionStatus(completedStatus), HttpStatus.OK);
	}

	@GetMapping("/filter/duedate/{dueDate}")
	public ResponseEntity<List<TaskDTO>> filterTaskByDueDate(@PathVariable LocalDate dueDate) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByDueDate(dueDate), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{completedStatus}/{dueDate}")
	public ResponseEntity<List<TaskDTO>> filterTaskByCompletionStatusAndDueDate(@PathVariable Boolean completedStatus, @PathVariable LocalDate dueDate) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByCompletionStatusAndDueDate(completedStatus, dueDate), HttpStatus.OK);
	}
	
}
