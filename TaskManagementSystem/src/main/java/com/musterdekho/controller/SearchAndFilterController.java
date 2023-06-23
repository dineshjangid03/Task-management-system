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
import com.musterdekho.exception.UserNotLoggedInException;
import com.musterdekho.service.SearchAndFilterService;

@RestController
@RequestMapping("/api")
public class SearchAndFilterController{
	
	@Autowired
	private SearchAndFilterService searchAndFilterService;

	@GetMapping("/search/title/{token}/{title}")
	public ResponseEntity<List<TaskDTO>> searchTaskByTitle(@PathVariable String token, @PathVariable String title) throws TaskNotFoundException, UserNotLoggedInException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskByTitle(token, title), HttpStatus.OK);
	}

	@GetMapping("/search/desc/{token}/{desc}")
	public ResponseEntity<List<TaskDTO>> searchTaskByDescription(@PathVariable String token, @PathVariable String desc) throws TaskNotFoundException, UserNotLoggedInException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskByDescription(token, desc), HttpStatus.OK);
	}

	@GetMapping("/search/user/{userId}")
	public ResponseEntity<List<TaskDTO>> searchTaskOfUser(@PathVariable Long userId) throws TaskNotFoundException, UserNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.searchTaskOfUser(userId), HttpStatus.OK);
	}

	@GetMapping("/filter/completionstatus/{token}/{completedStatus}")
	public ResponseEntity<List<TaskDTO>> filterTaskByCompletionStatus(@PathVariable String token, @PathVariable Boolean completedStatus) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByCompletionStatus(token, completedStatus), HttpStatus.OK);
	}

	@GetMapping("/filter/duedate/{token}/{dueDate}")
	public ResponseEntity<List<TaskDTO>> filterTaskByDueDate(@PathVariable String token, @PathVariable LocalDate dueDate) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByDueDate(token, dueDate), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{token}/{completedStatus}/{dueDate}")
	public ResponseEntity<List<TaskDTO>> filterTaskByCompletionStatusAndDueDate(@PathVariable String token, @PathVariable Boolean completedStatus, @PathVariable LocalDate dueDate) throws TaskNotFoundException {
		return new ResponseEntity<List<TaskDTO>>(searchAndFilterService.filterTaskByCompletionStatusAndDueDate(token, completedStatus, dueDate), HttpStatus.OK);
	}
	
}
