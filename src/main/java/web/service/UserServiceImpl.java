package web.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dto.UserDto;
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
    public Optional<User> getUserById(long id) {
        return userDao.findById(id);
    }
    
    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getLastName(), userDto.getAge());
        userDao.saveUser(user);
        userDto.setId(user.getId());
        
        logger.info("Пользователь {} сохранен в БД", user);
    }
    
    @Override
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
        
        logger.info("Пользователь с id = {} удален из БД", id);
    }
    
    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        Optional<User> user = getUserById(userDto.getId());
        user.ifPresent(u -> userDao.updateUser(u));
        logger.info("Пользователь с id = {} обновлен", userDto.getId());
    }
    
}
