package com.appCent.toDoApp.services;

import com.appCent.toDoApp.model.ToDoTask;
import com.appCent.toDoApp.model.User;
import com.appCent.toDoApp.model.UserDTO;
import com.appCent.toDoApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(User user){

        try{
           if(user.getId() == null){
                //register
               if(userRepository.findByName(user.getName()) == null){
                   if(user.getTasks() == null){
                       user.setTasks(new ArrayList<ToDoTask>());
                   }
                   userRepository.save(user);
                   return user.getId();
               }else{
                   return "username should be unique";
               }
            }else{
                //id for login response
                return userRepository.findById(user.getId()).orElseThrow(null).getId();
            }
        }catch(Exception ex){
            //ex handling
            return "exception thrown" + ex;
        }
    }

    public String login(String name,String password){
        try{
            User temp = userRepository.findByName(name);
            if(temp == null){
                return "user not found";
            }else{
                if(temp.getPassword().equals(password)){
                    return temp.getId();
                }else{
                    return "password is incorrect";
                }
            }
        }catch(Exception ex){
            return "ex:" + ex;
        }

    }

    public UserDTO getUserInfo(String id){
        try{
            User temp = userRepository.findById(id).orElseThrow(null);
            if(temp == null){
                return null;
            }else{
                return new UserDTO(temp.getName(),temp.getEmail());
            }
        }catch(Exception ex){
            return null;
        }
    }


}
