package com.musterdekho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musterdekho.dto.TaskDTO;
import com.musterdekho.exception.TaskException;
import com.musterdekho.exception.TaskNotFoundException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.exception.UserNotLoggedInException;
import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.Task;
import com.musterdekho.model.User;
import com.musterdekho.repository.CurrentUserSessionRepo;
import com.musterdekho.repository.TaskRepository;
import com.musterdekho.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserRepo;

	@Override
	public TaskDTO createTask(String token, Task task) throws UserNotFoundException, UserNotLoggedInException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserNotFoundException(loggedInUser.getUserId()));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDTO updateTask(String token, Task updatedTask) throws TaskNotFoundException, UserNotLoggedInException, TaskException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		Task savedTask = taskRepo.findById(updatedTask.getId())
				.orElseThrow(() -> new TaskNotFoundException(updatedTask.getId()));
		
		if(savedTask.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
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
	public TaskDTO assignTaskToAnotherUser(String token, Long taskId, Long userId) 
			throws TaskNotFoundException, UserNotFoundException, 
			UserNotLoggedInException, TaskException {

		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		task.setAssignedUser(user);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public TaskDTO markTaskComplete(String token, Long taskId) throws TaskNotFoundException, UserNotLoggedInException, TaskException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		if(task.isCompleted())
			task.setCompleted(false);
		else 
			task.setCompleted(true);
		
		Task saved = taskRepo.save(task);
		
		TaskDTO dto = convertToTaskDTO(saved);
		
		return dto;
		
	}

	@Override
	public Task deleteTask(String token, Long taskId) throws TaskNotFoundException, UserNotLoggedInException, TaskException {

		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException(taskId));
		
		if(task.getAssignedUser().getId()+1 != loggedInUser.getUserId()+1)
			throw new TaskException("This is not your task! you canno't update it");
		
		
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
