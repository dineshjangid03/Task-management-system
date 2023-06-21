package com.musterdekho.service;

import javax.security.auth.login.LoginException;

import com.musterdekho.dto.UserDTO;
import com.musterdekho.exception.UserException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.LoginDTO;
import com.musterdekho.model.User;

public interface UserService {

	public UserDTO addUser(User user)  throws UserException ;

	public CurrentUserSession logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String token)throws LoginException;

	public UserDTO getUserById(Long userId)throws UserNotFoundException ;

}
