package net.examsection.springboot.repository;

import net.examsection.springboot.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block,Long> {

    // Define a custom method to retrieve the 'value' attribute from the 'Block' entity
    @Query("SELECT b.value FROM Block b")
    List<Integer> getBlockValues();
}
