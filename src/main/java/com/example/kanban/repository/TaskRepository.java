package com.example.kanban.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.kanban.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * @author Deepak Devaraj
 * This is the JPA Repository for the Task Entity
 */
@Repository
public interface TaskRepository extends JpaRepository<Task , Long> {
	public Task findByTaskId(@Param("taskId") Long taskId);
	public List<Task> findByStatus(@Param("status") String status);
}
