package matyliano.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matyliano.entity.User;
import matyliano.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public @ResponseBody
    ResponseEntity<List<User>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("Request to get user with id : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(id));
    }

    @PostMapping("/new")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        log.info("Request to add user : {}", user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user, @PathVariable Long id) {
        log.info("Request to update user with id : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Request to delete user with id : {}", id);
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
