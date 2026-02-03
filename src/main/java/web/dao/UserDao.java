package web.dao;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAllUsers();
    Optional<User> findById(long id);
    void saveUser(User user);
    void removeUserById(long id);
    void updateUser(User user);
}
