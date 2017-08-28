package com.Posts.demo.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DaoUser extends CrudRepository<User, Long> {

	public List<User> findByusername(String username);
	public List<User> findBypassword(String password);

}
