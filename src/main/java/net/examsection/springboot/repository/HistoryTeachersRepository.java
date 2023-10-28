package net.examsection.springboot.repository;

import net.examsection.springboot.model.HistoryTeachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryTeachersRepository extends JpaRepository<HistoryTeachers,Integer> {
}
