package net.examsection.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.examsection.springboot.model.AbsentStudent;
import net.examsection.springboot.model.User;

import net.examsection.springboot.ExamSectionApplication;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM student p WHERE p.block_no = ?1 AND p.date = ?2", nativeQuery = true)
	public List<User> findBlockByPRN(long blockNo, String selectedDate);

}