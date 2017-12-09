package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask/{id}")
    public TaskDto getTask(@PathVariable("id") String taskId) {
        return new TaskDto((long)1, "test title", "test content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask/{id}")
    public void deleteTask(@PathVariable("id") String taskId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask/{id}", params = {"title", "content"})
    public TaskDto updateTask(
            @PathVariable("id") String taskId,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        return new TaskDto((long)1, "Edited test title", "test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", params = {"id", "title", "content"})
    public void createTask(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

    }
}
