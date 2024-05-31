package ch.hearc.jee24.usersservice.web;

import ch.hearc.jee24.usersservice.service.User;
import ch.hearc.jee24.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseBody ResponseEntity<User> createUser(@RequestBody User user){
        User newUser;
        try {
            newUser = userService.create(user);
        } catch(NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    @ResponseBody ResponseEntity<User> readUser(@PathVariable String username){
        Optional<User> optionalUser = userService.get(username);
        return optionalUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseBody ResponseEntity<List<User>> readAllUsers(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{username}")
    @ResponseBody ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username){
        User updatedUser;
        try {
            updatedUser = userService.update(username, user);
        } catch(NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    @ResponseBody ResponseEntity<User> deleteUser(@PathVariable String username){
        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    @ResponseBody ResponseEntity<Boolean> authenticateUser(@RequestBody User user){
        Boolean authenticationValid = false;
        try {
            authenticationValid = userService.authenticate(user);
        } catch(NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(authenticationValid, HttpStatus.OK);
    }
}
