package poc.taskmanager.taskmanagement.controller

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import poc.taskmanager.IntegrationTests
import poc.taskmanager.taskmanagement.enums.TaskStatusEnum.COMPLETED
import poc.taskmanager.taskmanagement.mapper.toDTO
import poc.taskmanager.taskmanagement.mapper.toEntity
import poc.taskmanager.taskmanagement.request.CreateTaskRequest
import poc.taskmanager.taskmanagement.request.UpdateTaskRequest

class UpdateTaskControllerTest : IntegrationTests() {

    @Test
    fun `Should update a task with success`() {
        val entity = CreateTaskRequest(
            title = "Test",
            description = "Test"
        )

        val entityBefore = repository.save(
            entity.toDTO().toEntity(
                description = entity.description
            )
        )

        val entityUpdates = UpdateTaskRequest(
            title = "Atualizado",
            description = "Atualizado",
            status = COMPLETED.name,
        )

        mockMvc.perform(
            patch("/tasks/{id}", entityBefore.id)
                .content(objectMapper.writeValueAsString(entityUpdates))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title", Matchers.equalTo(entityUpdates.title)))
            .andExpect(jsonPath("$.description", Matchers.equalTo(entityUpdates.description)))
            .andExpect(jsonPath("$.status", Matchers.equalTo(entityUpdates.status)))

        val entityAfter = repository.findAll().first()

        assertThat(entityAfter.title).isEqualTo(entityUpdates.title)
        assertThat(entityAfter.description).isEqualTo(entityUpdates.description)
    }

    @Test
    fun `Should not update a task with success when status not allowed`() {
        val notAllowedStatus = "TODOS"
        val entity = CreateTaskRequest(
            title = "Test",
            description = "Test"
        )

        val entityBefore = repository.save(
            entity.toDTO().toEntity(
                description = entity.description
            )
        )

        val entityUpdates = UpdateTaskRequest(
            title = "Atualizado",
            description = "Atualizado",
            status = notAllowedStatus,
        )

        mockMvc.perform(
            patch("/tasks/{id}", entityBefore.id)
                .content(objectMapper.writeValueAsString(entityUpdates))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }
}
