package com.musterdekho.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musterdekho.model.CurrentUserSession;
import com.musterdekho.model.LoginDTO;
import com.musterdekho.model.User;
import com.musterdekho.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<String>("Welcome",HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.addUser(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CurrentUserSession> loginUser(@RequestBody LoginDTO dto) throws LoginException {
        return new ResponseEntity<CurrentUserSession>(userService.logIntoAccount(dto),HttpStatus.OK);
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<String> logoutUser(@PathVariable("token") String token) throws LoginException {
        return new ResponseEntity<String>(userService.logOutFromAccount(token),HttpStatus.OK);
    }
    
}
