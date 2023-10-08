package net.examsection.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.examsection.springboot.model.BlocksAndStrengths;
public interface StrngthRepository extends JpaRepository<BlocksAndStrengths, Integer>{
	@Query(value="SELECT * FROM examsection.strengths_table where building=\"Main Building\";", nativeQuery=true)
	List<BlocksAndStrengths> mainbuilding();
	@Query(value="SELECT * FROM examsection.strengths_table where building=\"New Building\";", nativeQuery=true)
	List<BlocksAndStrengths> newbuilding();

}
