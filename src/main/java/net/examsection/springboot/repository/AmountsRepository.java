package net.examsection.springboot.repository;

import net.examsection.springboot.model.Amounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmountsRepository extends JpaRepository<Amounts,Long> {
    Amounts findByPost(String post);
}
