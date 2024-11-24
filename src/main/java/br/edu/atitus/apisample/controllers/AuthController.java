package br.edu.atitus.apisample.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.apisample.dtos.SignupDTO;
import br.edu.atitus.apisample.entities.TypeUser;
import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	 

	private final UserService userService;
	
	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<UserEntity> postSignup(@RequestBody SignupDTO signup) throws Exception {
			
		UserEntity newUser = new UserEntity();
		BeanUtils.copyProperties(signup, newUser);
		newUser.setType(TypeUser.Common);
		
	
		userService.save(newUser);
		
		return ResponseEntity.ok(newUser);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerMethod(Exception ex){
		String msg = ex.getMessage().replaceAll("\r\n", "");
		return ResponseEntity.badRequest().body(msg);
	}

}