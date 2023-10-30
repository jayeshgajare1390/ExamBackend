package net.examsection.springboot.service;

import java.util.List;

import net.examsection.springboot.model.User;

public interface UserService {
	public void addUser(User obj);
	public List<User> findBlockByPRN(long PRN,String date);
	  public List<User> getAllBlocks();
}
