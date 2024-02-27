package poc.taskmanager.taskmanagement.controller

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import poc.taskmanager.IntegrationTests
import poc.taskmanager.taskmanagement.request.CreateTaskRequest

class CreateTaskControllerTest : IntegrationTests() {

    @Test
    fun `Should create a task with success`() {
        val request = CreateTaskRequest(
            title = "Teste",
            description = "Teste"
        )

        mockMvc.perform(
            post("/tasks")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title", equalTo(request.title)))
            .andExpect(jsonPath("$.description", equalTo(request.description)))

        val entity = repository.findAll().first()

        assertThat(entity.title).isEqualTo(request.title)
        assertThat(entity.description).isEqualTo(request.description)
    }
}
