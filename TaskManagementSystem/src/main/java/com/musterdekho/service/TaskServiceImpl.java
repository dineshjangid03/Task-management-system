package com.musterdekho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.Task;
import com.musterdekho.model.User;
import com.musterdekho.repository.TaskRepository;
import com.musterdekho.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public TaskDTO createTask(Long userId, Task task) throws UserNotFoundException {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDTO updateTask(Task updatedTask) throws TaskNotFoundException {
		
		Task savedTask = taskRepo.findById(updatedTask.getId())
				.orElseThrow(() -> new TaskNotFoundException(updatedTask.getId()));
		
		if(updatedTask.getDescription()!=null)
			savedTask.setDescription(updatedTask.getDescription());
		
		if(updatedTask.getTitle()!=null)
			savedTask.setTitle(updatedTask.getTitle());
		
		if(updatedTask.getDueDate()!=null)
			savedTask.setDueDate(updatedTask.getDueDate());
		
		Task saved = taskRepo.save(savedTask);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDTO assignTaskToAnotherUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {

		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDTO markTaskComplete(Long taskId) throws TaskNotFoundException {
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		if(task.isCompleted())
			task.setCompleted(false);
		else 
			task.setCompleted(true);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public Task deleteTask(Long taskId) throws TaskNotFoundException {

		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		taskRepo.delete(task);
		
		return task;
	}

	@Override
	public TaskDTO getTaskById(Long taskId) throws TaskNotFoundException {
		
		Task saved = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}
	
	private TaskDTO convertToTaskDTO(Task task) {
		
		TaskDTO dto = new TaskDTO();
		
		dto.setCompleted(task.isCompleted());
		dto.setDescription(task.getDescription());
		dto.setDueDate(task.getDueDate());
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setName(task.getAssignedUser().getName());
		dto.setUserId(task.getAssignedUser().getId());
		
		return dto;
		
	}

}
