package com.musterdekho.service;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskException;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.exception.UserNotLoggedInException;
import com.musterdekho.model.Task;

public interface TaskService {

	/**
	 * Create a new task for the logged-in user.
	 *
	 * @param token The authentication token associated with the user session.
	 * @param task The task object to be created.
	 * @return The task DTO of the created task.
	 * @throws UserNotFoundException If the user is not found.
	 * @throws UserNotLoggedInException If the user is not logged in.
	 */
    public TaskDTO createTask(String token, Task task) throws UserNotFoundException, UserNotLoggedInException ;

    /**
     * Update an existing task for the logged-in user.
     *
     * @param token The authentication token associated with the user session.
     * @param updatedTask The updated task object.
     * @return The task DTO of the updated task.
     * @throws TaskNotFoundException If the task is not found.
     * @throws UserNotLoggedInException If the user is not logged in.
     * @throws TaskException If the task does not belong to the user.
     */
    public TaskDTO updateTask(String token, Task updatedTask) throws TaskNotFoundException, UserNotLoggedInException, TaskException;
    
    /**
     * Assign a task to another user for the logged-in user.
     *
     * @param token The authentication token associated with the user session.
     * @param taskId The ID of the task to be assigned.
     * @param userId The ID of the user to whom the task is assigned.
     * @return The task DTO of the updated task.
     * @throws TaskNotFoundException If the task is not found.
     * @throws UserNotFoundException If the user is not found.
     * @throws UserNotLoggedInException If the user is not logged in.
     * @throws TaskException If the task does not belong to the user.
     */
    public TaskDTO assignTaskToAnotherUser(String token, Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException, UserNotLoggedInException, TaskException ;

    /**
     * Mark a task as complete or incomplete for the logged-in user.
     *
     * @param token The authentication token associated with the user session.
     * @param taskId The ID of the task to be marked.
     * @return The task DTO of the updated task.
     * @throws TaskNotFoundException If the task is not found.
     * @throws UserNotLoggedInException If the user is not logged in.
     * @throws TaskException If the task does not belong to the user.
     */
    public TaskDTO markTaskComplete(String token, Long taskId) throws TaskNotFoundException , UserNotLoggedInException, TaskException ;

    /**
     * Delete a task for the logged-in user.
     *
     * @param token The authentication token associated with the user session.
     * @param taskId The ID of the task to be deleted.
     * @return The deleted task object.
     * @throws TaskNotFoundException If the task is not found.
     * @throws UserNotLoggedInException If the user is not logged in.
     * @throws TaskException If the task does not belong to the user.
     */
    public Task deleteTask(String token, Long taskId) throws TaskNotFoundException , UserNotLoggedInException, TaskException ;
 
    /**
     * Get a task by the provided task ID.
     *
     * @param taskId The ID of the task to retrieve.
     * @return The task DTO of the requested task.
     * @throws TaskNotFoundException If the task is not found.
     */
    public TaskDTO getTaskById(Long taskId) throws TaskNotFoundException ;
    
}
