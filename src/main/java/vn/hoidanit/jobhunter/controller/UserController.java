package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.error.IdInvalidException;
import vn.hoidanit.jobhunter.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
       return this.userService.createUser(user);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable("id") long id){
        this.userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") long id) throws IdInvalidException{
            return this.userService.findById(id);
    }

    @GetMapping("/user")
    public List<User> findAllUser(){
        return this.userService.finaAllUsers();
    }


}
