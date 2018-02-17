package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DbService dbService;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<Task> taskList = Arrays.asList(
                new Task(1L, "test title 1", "test 1"),
                new Task(2L, "test title 2", "test 2"),
                new Task(3L, "test title 3", "test 3"));
        List<TaskDto> taskDtoList = Arrays.asList(
                new TaskDto(1L, "test title 1", "test 1"),
                new TaskDto(2L, "test title 2", "test 2"),
                new TaskDto(3L, "test title 3", "test 3"));

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When&Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test()
    public void shouldThrowTaskNotFoundException() throws Exception {
        //Given
        when(dbService.getTask(anyLong())).thenReturn(Optional.empty());

        //When&Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task 1", "test");
        TaskDto taskDto = new TaskDto(1L, "test task 1", "test");

        when(dbService.getTask(anyLong())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When&Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test task 1")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void shouldFetchEmptyFoundTaskList() throws Exception {
        //Given
        when(dbService.findTasks(anyString())).thenReturn(new ArrayList<>());
        //When&Then
        mockMvc.perform(get("/v1/tasks/find/?beginLetters=abc").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task 1", "test");
        Task savedTask = new Task(1L, "test task 1", "test");
        TaskDto taskDto = new TaskDto(1L, "test task 1", "test");
        TaskDto requestBodyTaskDto = new TaskDto(1L, "test task 1", "test");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(savedTask);
        when(taskMapper.mapToTaskDto(savedTask)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(requestBodyTaskDto);

        //When&Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test task 1")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        //When&Then
        mockMvc.perform(delete("/v1/tasks/1"))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto requestBodyTaskDto = new TaskDto(1L, "test task 1", "test");
        Task task = new Task(1L, "test task 1", "test");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(requestBodyTaskDto);
        when(taskMapper.mapToTask(requestBodyTaskDto)).thenReturn(task);

        //When&Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(dbService, times(1)).saveTask(task);
    }
}