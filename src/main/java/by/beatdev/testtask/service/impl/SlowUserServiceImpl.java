package by.beatdev.testtask.service.impl;

import by.beatdev.testtask.entity.User;
import by.beatdev.testtask.repository.UserRepository;
import by.beatdev.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlowUserServiceImpl implements UserService {

    private static final int DELAY_MILLISECOND_MIN = 5000;
    private static final int DELAY_MILLISECOND_MAX = 10000;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        delay();
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        delay();
        return userRepository.findById(id);
    }

    private void delay() {
        try {

            Thread.sleep((((int) (Math.random() * (DELAY_MILLISECOND_MAX - DELAY_MILLISECOND_MIN)))) + DELAY_MILLISECOND_MIN);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
