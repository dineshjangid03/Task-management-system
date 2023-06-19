package com.musterdekho.service;

import javax.security.auth.login.LoginException;

import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.LoginDTO;
import com.musterdekho.model.User;

public interface UserService {
	
	public User addUser(User user);
	
	public CurrentUserSession logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String token)throws LoginException;

}
