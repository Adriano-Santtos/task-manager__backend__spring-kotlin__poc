package poc.taskmanager.taskmanagement.repository

import org.springframework.data.jpa.repository.JpaRepository
import poc.taskmanager.taskmanagement.entities.TaskEntity

interface TaskRepository : JpaRepository<TaskEntity, Long>
