package vn.hoidanit.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
       return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successful");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") long id) throws IdInvalidException{
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.finaAllUsers());
    }

    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws IdInvalidException{
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

}
