package com.musterdekho.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musterdekho.dto.TaskDTO;
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
public class SearchAndFilterServiceImpl implements SearchAndFilterService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserRepo;

	@Override
	public List<TaskDTO> searchTaskByTitle(String token, String title) throws TaskNotFoundException, UserNotLoggedInException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		List<Task> tasks = taskRepo.findByAssignedUserIdAndTitleContainingIgnoreCase(loggedInUser.getUserId(), title);
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> searchTaskByDescription(String token, String description) throws TaskNotFoundException, UserNotLoggedInException {
		
List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		List<Task> tasks = taskRepo.findByAssignedUserIdAndDescriptionContainingIgnoreCase(loggedInUser.getUserId(), description);
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> searchTaskOfUser(Long userId) throws TaskNotFoundException, UserNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		if(user.getTasks().isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(user.getTasks());
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> filterTaskByCompletionStatus(String token, Boolean completedStatus) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserNotFoundException(loggedInUser.getUserId()));
		
		
		List<Task> tasks = user.getTasks();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.isCompleted() == completedStatus)
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> filterTaskByDueDate(String token, LocalDate dueDate) throws TaskNotFoundException, UserNotLoggedInException, UserNotFoundException {
		
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(token);
		
		if(validation.size()==0) {
			throw new UserNotLoggedInException();
		}
		
		CurrentUserSession loggedInUser = validation.get(0);
		
		User user = userRepo.findById(loggedInUser.getUserId())
				.orElseThrow(() -> new UserNotFoundException(loggedInUser.getUserId()));
		
		
		List<Task> tasks = user.getTasks();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> filterTaskByCompletionStatusAndDueDate(String token, Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException {

		List<Task> tasks = taskRepo.findAll();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate)&&
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}
	
	
	private List<TaskDTO> convertToTaskDTO(List<Task> tasks) {
		
		List<TaskDTO> dtos = new ArrayList<>();
		
		tasks.forEach( task -> {
			
			TaskDTO dto = new TaskDTO();
			
			dto.setCompleted(task.isCompleted());
			dto.setDescription(task.getDescription());
			dto.setDueDate(task.getDueDate());
			dto.setId(task.getId());
			dto.setTitle(task.getTitle());
			dto.setName(task.getAssignedUser().getName());
			dto.setUserId(task.getAssignedUser().getId());
			dtos.add(dto);
			
		});
		
		return dtos;
		
	}
	
}
