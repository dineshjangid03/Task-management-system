package com.musterdekho.service;

import java.time.LocalDate;
import java.util.List;

import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;

public interface SearchAndFilterService {
	
	/**
	Search and Filtering:
   	Users should be able to search for tasks based on title, description, or assigned user.
  	Users should be able to filter tasks based on completion status and due date
	 */
	
	public List<Task> searchTaskByTitle(String title) throws TaskNotFoundException ;
	
	public List<Task> searchTaskByDescription(String description) throws TaskNotFoundException ;
	
	public List<Task> searchTaskOfUser(Long userId) throws TaskNotFoundException, UserNotFoundException ;
	
	public List<Task> filterTaskByCompletionStatus(Boolean completedStatus) throws TaskNotFoundException ;
	
	public List<Task> filterTaskByDueDate(LocalDate dueDate) throws TaskNotFoundException ;

	public List<Task> filterTaskByCompletionStatusAndDueDate(Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException ;
	
}
