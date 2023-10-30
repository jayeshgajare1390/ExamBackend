package net.examsection.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.examsection.springboot.model.FacultyPayment;



public interface FacultyPaymentRepository extends JpaRepository<FacultyPayment, Long> {
    List<FacultyPayment> findDistinctByYearAndStdAndSemAndExam(String year, String std, String sem, String exam);
    int countByFacultyNameAndYearAndStdAndSemAndExam(String facultyName, String year, String std, String sem, String exam);
}