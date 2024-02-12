package poc.taskmanager.taskmanagement.common.repository

import org.springframework.data.jpa.repository.JpaRepository
import poc.taskmanager.taskmanagement.common.entities.TaskEntity

interface TaskRepository : JpaRepository<TaskEntity, Long>
