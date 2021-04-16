package com.appCent.toDoApp.services;


import com.appCent.toDoApp.model.ToDoTask;
import com.appCent.toDoApp.model.User;
import com.appCent.toDoApp.repository.UserRepository;
import com.appCent.toDoApp.util.UtilMethods;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;



@AllArgsConstructor
@Service
public class TaskService {



    @Autowired
    private UserRepository userRepository;


    public List<ToDoTask> addTask(String id, ToDoTask taskToBeAdded){

        //check deadline for date
        try{
            if(taskToBeAdded.getDeadline() != null){
               if(taskToBeAdded.getDeadline().before(new Date())){
                    return null;
                }
            }
            //add
            User temp = userRepository.findById(id).orElseThrow(null);
            List<ToDoTask> newTasks = temp.getTasks();
            if(newTasks.size() == 0){
                //set initial id
                taskToBeAdded.setId(0);
            }else{
                //set new id
                taskToBeAdded.setId(newTasks.get(newTasks.size()-1).getId()+1);
            }

            newTasks.add(taskToBeAdded);
            temp.setTasks(newTasks);
            userRepository.save(temp);

            return temp.getTasks();

        }catch (Exception ex){
            return null;
        }

    }

    public List<ToDoTask> getTasks(String id){
        return userRepository.findById(id).orElseThrow(null).getTasks();
    }

    public List<ToDoTask> getTasksByDate(String id){
        //sorting
        List<ToDoTask> tempList = userRepository.findById(id).orElseThrow(null).getTasks();
        Collections.sort(tempList,(task1, task2)->(
                task1.getDeadline()==null)?
                (1)
                :
                ((task2.getDeadline()==null)?
                        (-1)
                        :
                        (task1.getDeadline().compareTo(task2.getDeadline()))));
        return tempList;
    }

    public List<ToDoTask> getTasksByPriority(String id){
        //sorting
        List<ToDoTask> tempList = userRepository.findById(id).orElseThrow(null).getTasks();
        Collections.sort(tempList,(task1, task2)->
                task1.getPriority().getValue()-task2.getPriority().getValue());
        return tempList;
    }

    public List<ToDoTask> update(String id, ToDoTask task){
        User temp = userRepository.findById(id).orElseThrow(null);
        List<ToDoTask> newTasks = temp.getTasks();
        for(int i = 0 ; i < newTasks.size(); i++){
            if(newTasks.get(i).getId() == task.getId()){
                newTasks.set(i,task);
            }
        }
        temp.setTasks(newTasks);
        userRepository.save(temp);
        return temp.getTasks();
    }

    public List<ToDoTask> removeTask(String id, long taskId){
        User temp = userRepository.findById(id).orElseThrow(null);
        List<ToDoTask> newTasks = temp.getTasks();
        for(int i = 0 ; i < newTasks.size(); i++){
            if(newTasks.get(i).getId() == taskId){
                newTasks.remove(i);
            }
        }
        temp.setTasks(newTasks);
        userRepository.save(temp);
        return temp.getTasks();

    }
}
