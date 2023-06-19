package com.musterdekho.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.LoginDTO;
import com.musterdekho.model.User;
import com.musterdekho.repository.CurrentUserSessionRepo;
import com.musterdekho.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private CurrentUserSessionRepo currentUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public User addUser(User user) {
		user.setTasks(new ArrayList<>());
		return userRepo.save(user);
	}
	
	@Override
	public CurrentUserSession logIntoAccount(LoginDTO dto) throws LoginException {
		List<User> list=userRepo.findByUsername(dto.getUsername());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid username");
		}
		
		User user=list.get(0);
		Optional<CurrentUserSession> validation=currentUserRepo.findById(user.getId());
		
		if(validation.isPresent()) {
			if(user.getPassword().equals(dto.getPassword())) {
				return validation.get();
			}
			throw new LoginException("Please enter valid password");
		}
		
		if(user.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession();
			cus.setUserId(user.getId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			currentUserRepo.save(cus);
			return cus;
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		List<CurrentUserSession> validation=currentUserRepo.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this username");
		}
		currentUserRepo.delete(validation.get(0));
		return "Logged out !";
	}

	

}


class RandomString {
	 
	static String make(int n){
	 
		String AlphaNumStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	 
		StringBuilder sb = new StringBuilder(n);
	 
		for (int i = 0; i < n; i++) {
	 
			int index = (int)(AlphaNumStr.length() * Math.random());
	 
			sb.append(AlphaNumStr.charAt(index));
		}
	 
		return sb.toString();
	}

}
