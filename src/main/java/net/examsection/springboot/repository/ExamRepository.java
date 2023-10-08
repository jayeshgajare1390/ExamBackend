package net.examsection.springboot.repository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

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
 }
