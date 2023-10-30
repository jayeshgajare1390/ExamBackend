package net.examsection.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import net.examsection.springboot.model.Teachers;

public interface TeachersRepository extends JpaRepository<Teachers,Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Teachers t WHERE t.teacher_name = :teacherName")
    void deleteByTeacherName(String teacherName);

}
