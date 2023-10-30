package net.examsection.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.examsection.springboot.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {

}
