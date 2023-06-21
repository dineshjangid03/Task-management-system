package com.musterdekho.service;

import javax.security.auth.login.LoginException;

import com.musterdekho.dto.UserDTO;
import com.musterdekho.exception.UserException;
import com.musterdekho.exception.UserNotFoundException;
import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.LoginDTO;
import com.musterdekho.model.User;

public interface UserService {

	/**
	 * Add a new user to the system.
	 *
	 * @param user The user object to be added.
	 * @return The user DTO of the added user.
	 * @throws UserException If an error occurs while adding the user.
	 */
	public UserDTO addUser(User user)  throws UserException ;

	/**
	 * Log into the user account using the provided login credentials.
	 *
	 * @param dto The login DTO containing the username and password.
	 * @return The current user session containing the user information and authentication token.
	 * @throws LoginException If the login credentials are invalid or an error occurs during the login process.
	 */
	public CurrentUserSession logIntoAccount(LoginDTO dto)throws LoginException;

	/**
	 * Log out from the user account using the provided authentication token.
	 *
	 * @param token The authentication token associated with the user session.
	 * @return A success message upon successful logout.
	 * @throws LoginException If the authentication token is invalid or an error occurs during the logout process.
	 */
	public String logOutFromAccount(String token)throws LoginException;

	/**
	 * Get the user information by the provided user ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The user DTO of the requested user.
	 * @throws UserNotFoundException If no user is found with the provided user ID.
	 */
	public UserDTO getUserById(Long userId)throws UserNotFoundException ;

}
