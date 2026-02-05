package web.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    
    private Logger logger;
    private UserDao userDao;
    
    public UserServiceImpl(@Qualifier("userDao") UserDao userDao, Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }
    
    @Override
    @Transactional
    public long addUser(String name, String lastName, Byte age) {
        User user = new User(name, lastName, age);
        userDao.saveUser(user);
        
        logger.info("Пользователь {} сохранен в БД", user);
        return user.getId();
    }
    
    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
        
        logger.info("Пользователь с id = {} удален из БД", id);
    }
    
    @Override
    @Transactional
    public void updateUser(Long id, String name, String lastName, Byte age) {
        Optional<User> user = getUserById(id);
        user.ifPresent(u -> userDao.updateUser(u));
        
        logger.info("Пользователь с id = {} обновлен", id);
    }
    
}
