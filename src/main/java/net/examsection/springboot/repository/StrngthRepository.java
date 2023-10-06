package net.examsection.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import net.examsection.springboot.model.BlocksAndStrengths;
public interface StrngthRepository extends JpaRepository<BlocksAndStrengths, Integer>{

}
