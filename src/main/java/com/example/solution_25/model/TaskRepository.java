package com.example.solution_25.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.isDone=false ORDER BY t.deadline")
    List<Task> findByIsDoneFalseOrderByDeadline();

    @Query("SELECT t FROM Task t WHERE t.isDone=true ORDER BY t.deadline")
    List<Task> findByIsDoneTrueOrderByDeadline();
}