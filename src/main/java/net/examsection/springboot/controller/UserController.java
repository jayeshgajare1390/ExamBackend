package net.examsection.springboot.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.examsection.springboot.model.AbsentStudent;
import net.examsection.springboot.model.User;

import net.examsection.springboot.repository.UserRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
//	//open postm
//	@Autowired
//	private UserServiceImplementation userServ;
//	
    private final UserRepository UserRepository;

    @Autowired
    public UserController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

//	@GetMapping("/blocks")
//    public List<User> getAllBlocks() {
//        return userServ.getAllBlocks();
//    }
	
	
//	@GetMapping("/blocks/{blockNo}")
//	public List<User> findBlockByPRN(@PathVariable long blockNo)
//	{
//		List<User> userList = new ArrayList<User>();
//		userList = this.userServ.findBlockByPRN(blockNo);
//		
//		return  userList;
//		
//	}
    @GetMapping("/blocks")
	public List<User> findBlockByPRN(
	    @RequestParam("blockNo") long blockNo,
	    @RequestParam("selectedDate") String selectedDate
	) {
	    List<User> userList = new ArrayList<>();
	    userList = this.UserRepository.findBlockByPRN(blockNo, selectedDate);
	    return userList;
	}

	
	
//	@GetMapping
//	public ResponseEntity<List<User>> getAllBlocks(@RequestParam(required = false) String name) {
//		try {
//			List<User> blockList  = UserRepository.getBlockFromDb(name);
//
//			
//			if (blockList.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//
//			return new ResponseEntity<>(blockList, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	
	

}

