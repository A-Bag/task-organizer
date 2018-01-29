package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        Task expectedTask = new Task(1L, "test title", "test content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(expectedTask, task);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto expectedTaskDto = new TaskDto(1L, "test title", "test content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(expectedTaskDto, taskDto);
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = Arrays.asList(new Task(1L, "test title 1", "test content 1"),
                new Task(2L, "test title 2", "test content 2"),
                new Task(3L, "test title 3", "test content 3"));
        List<TaskDto> expectedTaskDtoList = Arrays.asList(new TaskDto(1L, "test title 1", "test content 1"),
                new TaskDto(2L, "test title 2", "test content 2"),
                new TaskDto(3L, "test title 3", "test content 3"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(expectedTaskDtoList, taskDtoList);
    }
}