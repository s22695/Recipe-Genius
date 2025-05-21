package com.pro.client_service.user;

import com.pro.client_service.exception.UserNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Long createUser(UserRequest request) {
        var user = this.repository.save(mapper.toUser(request));
        return user.getId();
    }

    public void updateUser(UserRequest request) {
        var customer = this.repository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException(
                        "Cannot update user: No user found with such ID: " + request.id()
                ));
        mergeUser(customer, request);
        this.repository.save(customer);
    }

    private void mergeUser(User user, UserRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            user.setFirstName(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            user.setLastName(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            user.setEmail(request.email());
        }
    }

    public List<UserResponse> findAllUsers() {
        return  this.repository.findAll()
                .stream()
                .map(this.mapper::fromUser)
                .collect(Collectors.toList());
    }

    public UserResponse findById(Long id) {
        return this.repository.findById(id)
                .map(mapper::fromUser)
                .orElseThrow(() -> new UserNotFoundException("No user found with such ID: " + id));
    }

    public boolean existsById(Long id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }
}
