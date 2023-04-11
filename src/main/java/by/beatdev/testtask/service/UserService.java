package by.beatdev.testtask.service;

import by.beatdev.testtask.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
}
