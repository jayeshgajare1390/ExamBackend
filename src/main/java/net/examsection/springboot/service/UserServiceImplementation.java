package net.examsection.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.examsection.springboot.model.User;
import net.examsection.springboot.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	public void addUser(User obj) {
		
		this.userRepo.save(obj);
	}
	
	public List<User> findBlockByPRN(long PRN,String date) {
		
		return this.userRepo.findBlockByPRN(PRN,date);
	}

	@Override
	public List<User> getAllBlocks() {
		// TODO Auto-generated method stub
		return null;
	}



	public List<User> getBlockFromDb(String title) {
		List<User> block = new ArrayList<User>();

		if (title == null) 
			userRepo.findAll().forEach(block::add);


//		else
//			userRepo.findByDeveloperNameContainingIgnoreCase(title).forEach(developer::add);

		return  block;
	}
	

}

