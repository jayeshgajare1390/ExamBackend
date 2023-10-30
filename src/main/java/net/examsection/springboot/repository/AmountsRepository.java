package net.examsection.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.examsection.springboot.model.Amounts;

public interface AmountsRepository extends JpaRepository<Amounts, Long> {
    Amounts findByPost(String post);
}

