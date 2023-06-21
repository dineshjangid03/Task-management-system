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
import com.musterdekho.model.Task;
import com.musterdekho.model.User;
import com.musterdekho.repository.TaskRepository;
import com.musterdekho.repository.UserRepository;

@Service
public class SearchAndFilterServiceImpl implements SearchAndFilterService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<TaskDTO> searchTaskByTitle(String title) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findByTitleContainingIgnoreCase(title);
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		List<TaskDTO> dtos = convertToTaskDTO(tasks);
		
		return dtos;
		
	}

	@Override
	public List<TaskDTO> searchTaskByDescription(String description) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findByDescriptionContainingIgnoreCase(description);
		
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
	public List<TaskDTO> filterTaskByCompletionStatus(Boolean completedStatus) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findAll();
		
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
	public List<TaskDTO> filterTaskByDueDate(LocalDate dueDate) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findAll();
		
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
	public List<TaskDTO> filterTaskByCompletionStatusAndDueDate(Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException {

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
