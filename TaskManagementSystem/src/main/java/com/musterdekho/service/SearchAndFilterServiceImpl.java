package com.musterdekho.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Task> searchTaskByTitle(String title) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findByTitleContainingIgnoreCase(title);
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		return tasks;
		
	}

	@Override
	public List<Task> searchTaskByDescription(String description) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findByDescriptionContainingIgnoreCase(description);
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		return tasks;
		
	}

	@Override
	public List<Task> searchTaskOfUser(Long userId) throws TaskNotFoundException, UserNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
		
		if(user.getTasks().isEmpty())
			throw new TaskNotFoundException();
		
		return user.getTasks();
		
	}

	@Override
	public List<Task> filterTaskByCompletionStatus(Boolean completedStatus) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findAll();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.isCompleted() == completedStatus)
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		return tasks;
		
	}

	@Override
	public List<Task> filterTaskByDueDate(LocalDate dueDate) throws TaskNotFoundException {
		
		List<Task> tasks = taskRepo.findAll();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		return tasks;
		
	}

	@Override
	public List<Task> filterTaskByCompletionStatusAndDueDate(Boolean completedStatus, LocalDate dueDate) throws TaskNotFoundException {

		List<Task> tasks = taskRepo.findAll();
		
		tasks = tasks.stream()
				.filter( task -> 
				task.getDueDate().equals(dueDate)&&
				task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		
		if(tasks.isEmpty())
			throw new TaskNotFoundException();
		
		return tasks;
		
	}
	
}
