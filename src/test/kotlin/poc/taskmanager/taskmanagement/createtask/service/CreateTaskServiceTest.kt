package poc.taskmanager.taskmanagement.createtask.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.taskmanager.UnitTests
import poc.taskmanager.taskmanagement.common.repository.TaskRepository
import poc.taskmanager.taskmanagement.createtask.dto.CreateTaskDTO
import poc.taskmanager.taskmanagement.createtask.toEntity
import java.util.UUID

class CreateTaskServiceTest : UnitTests() {

    @MockK
    lateinit var repository: TaskRepository

    @InjectMockKs
    lateinit var service: CreateTaskService

    @Test
    fun `should create a new task with success`() {
        // Arrange
        val dto = CreateTaskDTO(
            title = UUID.randomUUID().toString(),
            description = UUID.randomUUID().toString(),
        )

        val entity = dto.toEntity(description = dto.description!!)

        every { repository.save(any()) } returns entity

        // Act
        val createdTask = service.create(dto)

        // Assert
        assertThat(createdTask.title).isEqualTo(entity.title)
        assertThat(createdTask.description).isEqualTo(entity.description)
    }

    @Test
    fun `should save description as EMPTY_VALUE when is null is informed`() {
        // Arrange
        val dto = CreateTaskDTO(
            title = UUID.randomUUID().toString(),
            description = null,
        )

        val entity = dto.toEntity(description = EMPTY_VALUE)

        every { repository.save(any()) } returns entity

        // Act
        val createdTask = service.create(dto)

        // Assert
        assertThat(createdTask.title).isEqualTo(entity.title)
        assertThat(createdTask.description).isEqualTo(entity.description)
    }
}
