package net.examsection.springboot.repository;
import java.util.List;

import net.examsection.springboot.model.AbsentStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
@Repository
public interface AbsentStudentRepository extends JpaRepository<AbsentStudent, Long> {
    List<AbsentStudent> findByyear(String classs);
    @Query("SELECT a FROM AbsentStudent a WHERE a.examyear = ?1 AND a.year = ?2 AND a.examination = ?3")
    List<AbsentStudent> findCustomAbsentStudents(String examYear, String year, String examination);
//	boolean existsByBlock_noAndDate(Integer block_no, String date);
//	@Query(value = "SELECT COUNT(*) > 0 FROM AbsentStudent a WHERE a.date = :date AND a.block_no = :blockNo", nativeQuery = true)
//    boolean existsByDateAndBlockNo(@Param("date") String date, @Param("blockNo") Integer blockNo);
//	 List<AbsentStudent> findByBlockNoAndDate(Integer blockNumber, String date);

    @Query(value = "SELECT * FROM absent_student WHERE block_no = ?1 AND date = ?2", nativeQuery = true)
    List<AbsentStudent> findAbsentStudentsByBlockNoAndDate(Integer blockNo, String date);
    @Modifying
    @Transactional
    @Query("DELETE FROM AbsentStudent a WHERE a.prn = :prn AND a.block_no = :blockNo AND a.date = :date")
    void deleteByPrnAndBlockNoAndDate(@Param("prn") int prn, @Param("blockNo") Integer blockNo, @Param("date") String date);
}
