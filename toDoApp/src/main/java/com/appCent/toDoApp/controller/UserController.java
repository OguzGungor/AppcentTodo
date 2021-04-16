package com.appCent.toDoApp.controller;

import com.appCent.toDoApp.configuration.SwaggerConfig;
import com.appCent.toDoApp.model.User;
import com.appCent.toDoApp.model.UserDTO;
import com.appCent.toDoApp.services.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Api(tags = {SwaggerConfig.USER_CONTROLLER_TAG})
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public String rootGetTest(){
        return "root get service is fine";
    }

    @PostMapping
    public String rootPostTest(){
        return "root post service is fine";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        return service.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password){
       // return name + " : " + password;
        return service.login(name,password);
    }

    @GetMapping("/info")
    public UserDTO info(@RequestParam String id){
        return service.getUserInfo(id);
    }


}
