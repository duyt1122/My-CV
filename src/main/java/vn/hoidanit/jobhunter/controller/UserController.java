package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

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
}
