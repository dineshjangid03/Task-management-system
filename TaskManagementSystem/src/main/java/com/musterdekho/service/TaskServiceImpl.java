package com.musterdekho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Task createTask(Long userId, Task task) throws UserNotFoundException {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		task.setAssignedUser(user);
		
		return taskRepo.save(task);
		
	}

	@Override
	public Task updateTask(Task updatedTask) throws TaskNotFoundException {
		
		Task savedTask = taskRepo.findById(updatedTask.getId())
				.orElseThrow(() -> new TaskNotFoundException(updatedTask.getId()));
		
		if(updatedTask.getDescription()!=null)
			savedTask.setDescription(updatedTask.getDescription());
		
		if(updatedTask.getTitle()!=null)
			savedTask.setTitle(updatedTask.getTitle());
		
		if(updatedTask.getDueDate()!=null)
			savedTask.setDueDate(updatedTask.getDueDate());
		
		return taskRepo.save(savedTask);
		
	}

	@Override
	public Task assignTaskToAnotherUser(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {

		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		task.setAssignedUser(user);
		
		return taskRepo.save(task);
		
	}

	@Override
	public Task markTaskComplete(Long taskId) throws TaskNotFoundException {
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		if(task.isCompleted())
			task.setCompleted(false);
		else 
			task.setCompleted(true);
		
		return taskRepo.save(task);
		
	}

	@Override
	public Task deleteTask(Long taskId) throws TaskNotFoundException {

		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		taskRepo.delete(task);
		
		return task;
	}

	@Override
	public Task getTaskById(Long taskId) throws TaskNotFoundException {
		
		return taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
	}

}
