package net.examsection.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.examsection.springboot.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
