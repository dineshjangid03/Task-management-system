package com.musterdekho.service;

import java.time.LocalDate;
import java.util.List;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;

public interface SearchAndFilterService {
	
	/**
	 * Search for tasks based on the provided title.
	 *
	 * @param title The title to search for.
	 * @return A list of task DTOs that match the provided title.
	 * @throws TaskNotFoundException If no tasks are found with the provided title.
	 */
	public List<TaskDTO> searchTaskByTitle(String title) throws TaskNotFoundException ;
	
	/**
	 * Search for tasks based on the provided description.
	 *
	 * @param description The description to search for.
	 * @return A list of task DTOs that match the provided description.
	 * @throws TaskNotFoundException If no tasks are found with the provided description.
	 */
	public List<TaskDTO> searchTaskByDescription(String description) throws TaskNotFoundException ;
	
	/**
	 * Retrieve tasks assigned to the specified user.
	 *
	 * @param userId The ID of the user.
	 * @return A list of task DTOs assigned to the specified user.
	 * @throws TaskNotFoundException If no tasks are found for the specified user.
	 * @throws UserNotFoundException If the specified user is not found.
	 */
	public List<TaskDTO> searchTaskOfUser(Long userId) throws TaskNotFoundException, UserNotFoundException ;
	
	/**
	 * Filter tasks based on the provided completion status.
	 *
	 * @param completedStatus The completion status to filter tasks by.
	 * @return A list of task DTOs that match the provided completion status.
	 * @throws TaskNotFoundException If no tasks are found with the provided completion status.
	 */
	public List<TaskDTO> filterTaskByCompletionStatus(Boolean completedStatus) throws TaskNotFoundException ;
	
	/**
	 * Filter tasks based on the provided due date.
	 *
	 * @param dueDate The due date to filter tasks by.
	 * @return A list of task DTOs that match the provided due date.
	 * @throws TaskNotFoundException If no tasks are found with the provided due date.
	 */
	public List<TaskDTO> filterTaskByDueDate(LocalDate dueDate) throws TaskNotFoundException ;

	/**
	 * Filter tasks based on the provided completion status and due date.
	 *
	 * @param completedStatus The completion status to filter tasks by.
	 * @param dueDate         The due date to filter tasks by.
	 * @return A list of task DTOs that match the provided completion status and due date.
	 * @throws TaskNotFoundException If no tasks are found with the provided completion status and due date.
	 */
	public List<TaskDTO> filterTaskByCompletionStatusAndDueDate(Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException ;
	
}
