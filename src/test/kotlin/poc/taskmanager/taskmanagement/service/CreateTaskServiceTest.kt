package poc.taskmanager.taskmanagement.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import poc.taskmanager.UnitTests
import poc.taskmanager.taskmanagement.builder.createTaskDTOBuilder
import poc.taskmanager.taskmanagement.mapper.toEntity
import poc.taskmanager.taskmanagement.repository.TaskRepository

class CreateTaskServiceTest : UnitTests() {

    @MockK
    lateinit var repository: TaskRepository

    @InjectMockKs
    lateinit var service: CreateTaskService

    @Test
    fun `should create a new task with success`() {
        // Arrange
        val dto = createTaskDTOBuilder()

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
        val dto = createTaskDTOBuilder(
            description = null
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
