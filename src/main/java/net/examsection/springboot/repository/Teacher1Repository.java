package net.examsection.springboot.repository;

import net.examsection.springboot.model.Teacher1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Teacher1Repository extends JpaRepository<Teacher1,Integer> {

    @Query(value = "SELECT block_no FROM testing.teacher1" ,nativeQuery=true)
    List<Integer> findDistinctBlockNo();

    // Method to delete all records in the table
    void deleteAll();
}
