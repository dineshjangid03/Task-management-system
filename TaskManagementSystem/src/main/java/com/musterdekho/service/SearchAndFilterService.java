package com.musterdekho.service;

import java.time.LocalDate;
import java.util.List;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.exception.UserNotLoggedInException;

public interface SearchAndFilterService {
	
	/**
	 * Search for tasks based on the provided title.
	 *
	 * @param token  The user token for authentication.
	 * @param title  The title to search for.
	 * @return A list of task DTOs that match the provided title.
	 * @throws TaskNotFoundException      If no tasks are found with the provided title.
	 * @throws UserNotLoggedInException   If the user is not logged in.
	 */
	public List<TaskDTO> searchTaskByTitle( String token, String title) throws TaskNotFoundException, UserNotLoggedInException ;
	
	/**
	 * Search for tasks based on the provided description.
	 *
	 * @param token       The user token for authentication.
	 * @param description The description to search for.
	 * @return A list of task DTOs that match the provided description.
	 * @throws TaskNotFoundException      If no tasks are found with the provided description.
	 * @throws UserNotLoggedInException   If the user is not logged in.
	 */
	public List<TaskDTO> searchTaskByDescription(String token, String description) throws TaskNotFoundException, UserNotLoggedInException ;
	
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
	 * @param token           The user token for authentication.
	 * @param completedStatus The completion status to filter tasks by.
	 * @return A list of task DTOs that match the provided completion status.
	 * @throws TaskNotFoundException       If no tasks are found with the provided completion status.
	 * @throws UserNotLoggedInException    If the user is not logged in.
	 * @throws UserNotFoundException       If the specified user is not found.
	 */
	public List<TaskDTO> filterTaskByCompletionStatus(String token, Boolean completedStatus) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException ;
	
	/**
	 * Filter tasks based on the provided due date.
	 *
	 * @param token    The user token for authentication.
	 * @param dueDate  The due date to filter tasks by.
	 * @return A list of task DTOs that match the provided due date.
	 * @throws TaskNotFoundException       If no tasks are found with the provided due date.
	 * @throws UserNotLoggedInException    If the user is not logged in.
     * @throws UserNotFoundException       If the specified user is not found.
	 */
	public List<TaskDTO> filterTaskByDueDate(String token, LocalDate dueDate) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException ;

	/**
	 * Filter tasks based on the provided completion status and due date.
	 *
	 * @param token           The user token for authentication.
	 * @param completedStatus The completion status to filter tasks by.
	 * @param dueDate         The due date to filter tasks by.
	 * @return A list of task DTOs that match the provided completion status and due date.
	 * @throws TaskNotFoundException If no tasks are found with the provided completion status and due date.
	 */
	public List<TaskDTO> filterTaskByCompletionStatusAndDueDate(String token, Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException ;
	
}
