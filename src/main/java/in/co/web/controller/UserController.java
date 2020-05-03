package in.co.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.we.form.UserForm;
import in.co.web.entities.User;
import in.co.web.response.BaseResponse;
import in.co.web.service.CustomUserDetailService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	CustomUserDetailService userService;

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		return "Hello " + name;
	}

	@GetMapping("/getAllUser")
	public List<User> getAllUser() {

		return userService.findAllUses();
	}

	@PostMapping(value="/register",consumes=MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse register(@RequestBody UserForm userForm) {
		return userService.register(userForm);

	}
	
	@PostMapping(value="/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse login(@RequestBody UserForm userForm)
	{
		return userService.authnticate(userForm);
	}

}
