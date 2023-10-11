package net.examsection.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import jakarta.transaction.Transactional;
import net.examsection.springboot.model.MalPracticeStudent;

@Repository
public interface MalPracticeStudentRepository extends JpaRepository<MalPracticeStudent, Long> {
	List<MalPracticeStudent> findByyear(String classs);
	@Query("SELECT a FROM MalPracticeStudent a WHERE a.examyear = ?1 AND a.year = ?2 AND a.examination = ?3")
    List<MalPracticeStudent> findCustomMalPracticeStudents(String examYear, String year, String examination);
	@Modifying
    @Transactional
    @Query("DELETE FROM MalPracticeStudent a WHERE a.prn = :prn AND a.block_no = :blockNo AND a.date = :date")
    void deleteByPrnAndBlockNoAndDate(@Param("prn") int prn, @Param("blockNo") Integer blockNo, @Param("date") String date);
	}
