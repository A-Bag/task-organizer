package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
    @Override
    List<Task> findAll();

    @Override
    Optional<Task> findById(Long id);

    @Override
    Task save(Task task);

    @Override
    void deleteById(Long id);

    @Query
    List<Task> findTasksByFirstThreeLetters(@Param("threeLetters") String threeLetters);
}
