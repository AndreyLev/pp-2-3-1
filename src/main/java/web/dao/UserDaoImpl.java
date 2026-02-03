package web.dao;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component(value = "userDao")
public class UserDaoImpl implements UserDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;
    
    public UserDaoImpl(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager
                                .createQuery("SELECT u FROM User u", User.class)
                                .getResultList();
        
        if (users == null || users.size() == 0) {
            logger.info("Пользователи в базе данных не найдены");
            return Collections.emptyList();
        } else{
            logger.info("Найдено {} пользователей в базе данных", users.size());
            return users;
        }
    }
    
    @Override
    public Optional<User> findById(long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            logger.info("Пользователь с id = {} не найден", id);
            return Optional.ofNullable(user);
        } else {
            logger.info("Пользователь с id = {} найден", id);
            return Optional.of(user);
        }
    }
    
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    
    @Override
    public void removeUserById(long id) {
        Optional<User> user = findById(id);
        user.ifPresent(u -> entityManager.remove(u));
    }
    
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
