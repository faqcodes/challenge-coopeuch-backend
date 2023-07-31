package com.coopeuch.challenge;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.coopeuch.challenge.domain.models.TaskRequest;
import com.coopeuch.challenge.domain.models.TaskResponse;
import com.coopeuch.challenge.domain.services.CreateTaskService;
import com.coopeuch.challenge.domain.services.CrudService;
import com.coopeuch.challenge.persistences.repositories.TaskRepository;

@WebMvcTest
@AutoConfigureMockMvc
public class TaskControllerGetTest {

        @MockBean
        private TaskRepository taskRepository;

        @MockBean
        private CrudService<TaskRequest, TaskResponse> taskService;

        @MockBean
        private CreateTaskService createTaskService;

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void whenGetRequestToController_AndValidTask_thenCorrectResponse() throws Exception {

                Mockito.when(createTaskService.create()).thenReturn(taskService);

                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .get("/api/tasks/v1/758583664")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void whenGetRequestToController_AndInvalidTask_thenCorrectResponse() throws Exception {

                Mockito.when(createTaskService.create()).thenReturn(taskService);

                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .get("/api/tasks/v1/task-id")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        public void whenGetAllRequestToController_AndValidTask_thenCorrectResponse() throws Exception {

                Mockito.when(createTaskService.create()).thenReturn(taskService);

                mockMvc.perform(
                                MockMvcRequestBuilders
                                                .get("/api/tasks/v1")
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

}