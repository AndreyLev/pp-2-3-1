package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    long addUser(String name, String lastName, Byte age);
    void removeUserById(Long id);
    void updateUser(Long id, String name, String lastName, Byte age);
}
