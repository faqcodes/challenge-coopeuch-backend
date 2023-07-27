package com.coopeuch.challenge;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.coopeuch.challenge.controllers.TaskController;
import com.coopeuch.challenge.domain.services.TaskService;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@WebMvcTest
@AutoConfigureMockMvc
public class TaskControllerCreateTest {

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskService taskService;

    @Autowired
    private TaskController taskController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToControllerAndValidTask_thenCorrectResponse() throws Exception {

        var task = "{\"description\": \"Descripcion Tarea 1\", \"active\" : \"true\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/tasks/v1")
                        .content(task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void whenPostRequestToControllerAndInvalidTask_thenCorrectResponse() throws Exception {

        var task = "{\"description\": \"\", \"active\" : \"true\"}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/tasks/v1")
                        .content(task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}