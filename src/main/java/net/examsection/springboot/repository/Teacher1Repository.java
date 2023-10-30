package net.examsection.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.examsection.springboot.model.Teacher1;

public interface Teacher1Repository extends JpaRepository<Teacher1,Long> {
    @Query(value = "SELECT block_no FROM testing.teacher1" ,nativeQuery=true)
    List<Integer> findDistinctBlockNo();
}
