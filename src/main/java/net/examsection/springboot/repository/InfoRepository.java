package net.examsection.springboot.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.examsection.springboot.model.InfoTable;
public interface InfoRepository extends JpaRepository<InfoTable, Integer> {
	 @Query(value="SELECT * FROM info_table where id=?1" , nativeQuery=true)
	 Optional<List<InfoTable>> findInfo(long InfoTableId);
	 @Query(value="SELECT e.block_no,e.prn,e.studentname,i.course,i.date,i.endtime,i.optionvalue,i.starttime,i.subject,i.year FROM studentdataexam e INNER JOIN info_table i ON e.info_table_id = i.id" ,nativeQuery=true)
	 List<Object[]> innerJoinQuery();
	 @Query(value="SELECT id from info_table order by id desc limit 1", nativeQuery=true)
	 int getlastentry();
	 @Query(value="SELECT * from info_table order by id desc", nativeQuery=true)
	List<InfoTable> findAlldesc();

	// New method to search data within a date range
	@Query(value = "SELECT date, slot FROM info_table WHERE date >= ?1 AND date <= ?2", nativeQuery = true)
	List<Object[]> findInfoByDateRange(String startDate, String endDate);
}
