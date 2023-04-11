package by.beatdev.testtask.controller;

import by.beatdev.testtask.dto.UserDTO;
import by.beatdev.testtask.dto.UserStatusDTO;
import by.beatdev.testtask.entity.NetworkStatus;
import by.beatdev.testtask.entity.User;
import by.beatdev.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<Long> createUser(@RequestBody User user)
    {
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getImageUrl(),user.getStatus());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/{status}")
    public ResponseEntity<UserStatusDTO> updateUserStatus(@PathVariable Long userId, @PathVariable String status) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            NetworkStatus previousStatus = user.getStatus();
            user.setStatus(NetworkStatus.valueOf(status));
            userService.save(user);
            UserStatusDTO updatedUserStatusDTO = new UserStatusDTO(user.getId(), previousStatus, user.getStatus());
            return new ResponseEntity<>(updatedUserStatusDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
