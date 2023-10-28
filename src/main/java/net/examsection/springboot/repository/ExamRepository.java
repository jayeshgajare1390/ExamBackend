package net.examsection.springboot.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import net.examsection.springboot.model.ExamSection;
//MyDataRepository.java
public interface ExamRepository extends JpaRepository<ExamSection, Long> {
	//ExamSection saveAll(ExamSection products);
	@Query("SELECT e FROM ExamSection e WHERE e.programname = ?1 AND e.infoTableId = ?2")
    List<ExamSection> findByProgramNameAndInfoTableId(String branch, int infoid);
	List<ExamSection> findByInfoTableId(long InfoTableId);
	 @Query("SELECT e FROM ExamSection e INNER JOIN InfoTable i ON e.infoTableId = i.id where e.infoTableId=?1")
	 List<ExamSection> getExamInfoWithInnerJoinByInfoTableId(int infoTableId);
	 @Query(value="SELECT count(info_table_id) from studentdataexam where info_table_id=?1" , nativeQuery=true)
	 int counter(int infoid);
	 @Query(value="SELECT * FROM examsection.studentdataexam order by id desc limit ?1" , nativeQuery=true)
	List<ExamSection> getallrecent(int totalCount);


	@Query("SELECT DISTINCT e.block_no FROM ExamSection e " +
			"INNER JOIN InfoTable i ON e.infoTableId = i.id " +
			"WHERE i.date BETWEEN ?1 AND ?2")
	List<Integer> findDistinctBlockNosInDateRange(String startDate, String endDate);
	@Query(value = "SELECT * FROM info_table i inner join studentdataexam s on i.info_table_id=s.id where i.block_no=?1;", nativeQuery = true)
	public List<Object[]> findBlockByPRN(int blockNo);
	 @Transactional
	 @Modifying
	 @Query(value = "DELETE FROM STUDENTDATAEXAM WHERE INFO_TABLE_ID = ?1", nativeQuery = true)
	 void deleteinfo(int id);
 }
