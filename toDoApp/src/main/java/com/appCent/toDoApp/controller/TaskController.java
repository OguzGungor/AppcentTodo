package com.appCent.toDoApp.controller;

import com.appCent.toDoApp.configuration.SwaggerConfig;
import com.appCent.toDoApp.model.ToDoTask;
import com.appCent.toDoApp.services.TaskService;
import com.appCent.toDoApp.services.UserService;
import com.appCent.toDoApp.util.Priority;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@Api(tags = {SwaggerConfig.TASK_CONTROLLER_TAG})
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/addTask")
    public List<ToDoTask> addTask(@RequestParam String id , @RequestBody ToDoTask taskToBeAdded){
        return service.addTask(id,taskToBeAdded);
    }

    @GetMapping("/getTasks")
    public List<ToDoTask> getTasks(@RequestParam String id){
        return service.getTasks(id);
    }

    @GetMapping("/getTasksByDate")
    public List<ToDoTask> getTasksByDate(@RequestParam String id){
        return service.getTasksByDate(id);
    }

    @GetMapping("/getTasksByPriority")
    public List<ToDoTask> getTasksByPriority(@RequestParam String id){
        return service.getTasksByPriority(id);
    }

    @PostMapping("/removeTask")
    public List<ToDoTask> removeTask(@RequestParam String id, @RequestParam long taskId ){
        return service.removeTask(id,taskId);
    }

    @PostMapping("updateTask")
    public List<ToDoTask> updateTask(@RequestParam String id, @RequestBody ToDoTask task){
        return service.update(id,task);
    }

}
