package com.iatech.api.user.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iatech.api.user.dao.UserDao;
import com.iatech.api.user.exception.UserNotFoundException;
import com.iatech.api.user.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;	

	@GetMapping
	public List<User> getAll() {
		logger.debug("Inicio del metodo getAll");
		return userDao.findAll();
	}
	
	@GetMapping(path="/{id}")
	public User  getById(@PathVariable Long id) {
		logger.debug("Inicio del metodo getById");
		Optional<User> user = userDao.findById(id);
		if(!user.isPresent()) {
			logger.warn("user not found");
			throw new UserNotFoundException("Id - " + id);
		}
		return user.get();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> update(@RequestBody User user,@PathVariable Long id) {
		logger.debug("Inicio del metodo update");
		Optional<User> userOptinal  = userDao.findById(id);
				
		if(!userOptinal.isPresent()) {
			logger.warn("user not found");
			throw new UserNotFoundException("Id - " + id);
		}
		user.setId(id);
		userDao.save(user);
		return ResponseEntity.ok().build();				
	}	
	
	@GetMapping(path="/search/{query}")
	public List<User> searchQuery(@PathVariable String query) {
		logger.debug("Inicio del metodo searchQuery");
		return userDao.searchQuery(query);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody User user) {
		logger.debug("Inicio del metodo create");
		User usernew = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usernew.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> removeById(@PathVariable Long id) {
		logger.debug("Inicio del metodo create");
		Optional<User> auxUser  =  userDao.findById(id);		
		if(!auxUser.isPresent()) {
			logger.warn("Product not found");
			throw new UserNotFoundException("Id - " + id);
		}
		userDao.delete(auxUser.get());
		return ResponseEntity.ok().build();
		
	}

}
