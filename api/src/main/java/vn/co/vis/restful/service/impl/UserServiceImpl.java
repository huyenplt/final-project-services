package vn.co.vis.restful.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.co.vis.common.dao.entity.User;
import vn.co.vis.restful.dao.repository.CustomUserRepository;
import vn.co.vis.common.dto.response.UserResponse;
import vn.co.vis.common.exception.ResourceNotFoundException;
import vn.co.vis.restful.service.AbstractService;
import vn.co.vis.restful.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Giang Thanh Quang
 * @since 10/28/2020
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    CustomUserRepository customUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserResponse> createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userRegistered = customUserRepository.save(user);
        return Optional.of(new UserResponse(userRegistered.getId(), userRegistered.getFirstName(),
                userRegistered.getLastName(), userRegistered.getEmail(), userRegistered.getPhone()));
    }

    @Override
    public Optional<UserResponse> getUser(String userName) {
        User user = customUserRepository.findByUsername(userName).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return Optional.of(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()));
    }

    @Override
    public Optional<List<UserResponse>> getUsers() {
        List<User> users = customUserRepository.findAll();
        return Optional.of(users.stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()))
                .collect(Collectors.toList()));
    }
}
