package net.examsection.springboot.repository;

import net.examsection.springboot.model.FacultyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyPaymentRepository extends JpaRepository<FacultyPayment,Long> {
    List<FacultyPayment> findDistinctByYearAndStdAndExam(String year, String std, String exam);
    int countByFacultyNameAndYearAndStdAndExam(String facultyName, String year, String std, String exam);
}
