package com.musterdekho.service;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;

public interface TaskService {

    public TaskDTO createTask(Long userId, Task task) throws UserNotFoundException;

    public TaskDTO updateTask(Task updatedTask) throws TaskNotFoundException;
    
    public TaskDTO assignTaskToAnotherUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException;

    public TaskDTO markTaskComplete(Long taskId) throws TaskNotFoundException ;

    public Task deleteTask(Long taskId) throws TaskNotFoundException ;
 
    public TaskDTO getTaskById(Long taskId) throws TaskNotFoundException ;
    
}
