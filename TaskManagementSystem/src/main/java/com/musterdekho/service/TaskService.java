package com.musterdekho.service;

import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;

public interface TaskService {
	
    public Task createTask(Long userId, Task task) throws UserNotFoundException;

    public Task updateTask(Task updatedTask) throws TaskNotFoundException;
    
    public Task assignTaskToAnotherUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException;
    
    public Task markTaskComplete(Long taskId) throws TaskNotFoundException ;
    
    public Task deleteTask(Long taskId) throws TaskNotFoundException ;
    
}
