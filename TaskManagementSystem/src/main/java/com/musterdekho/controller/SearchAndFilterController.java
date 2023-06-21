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
	public ResponseEntity<List<Task>> searchTaskByTitle(@PathVariable String title) throws TaskNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.searchTaskByTitle(title), HttpStatus.OK);
	}

	@GetMapping("/search/desc/{desc}")
	public ResponseEntity<List<Task>> searchTaskByDescription(@PathVariable String desc) throws TaskNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.searchTaskByDescription(desc), HttpStatus.OK);
	}

	@GetMapping("/search/user/{userId}")
	public ResponseEntity<List<Task>> searchTaskOfUser(@PathVariable Long userId) throws TaskNotFoundException, UserNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.searchTaskOfUser(userId), HttpStatus.OK);
	}

	@GetMapping("/filter/completionstatus/{completedStatus}")
	public ResponseEntity<List<Task>> filterTaskByCompletionStatus(@PathVariable Boolean completedStatus) throws TaskNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.filterTaskByCompletionStatus(completedStatus), HttpStatus.OK);
	}

	@GetMapping("/filter/duedate/{dueDate}")
	public ResponseEntity<List<Task>> filterTaskByDueDate(@PathVariable LocalDate dueDate) throws TaskNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.filterTaskByDueDate(dueDate), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{completedStatus}/{dueDate}")
	public ResponseEntity<List<Task>> filterTaskByCompletionStatusAndDueDate(@PathVariable Boolean completedStatus, @PathVariable LocalDate dueDate) throws TaskNotFoundException {
		return new ResponseEntity<List<Task>>(searchAndFilterService.filterTaskByCompletionStatusAndDueDate(completedStatus, dueDate), HttpStatus.OK);
	}
	
}
