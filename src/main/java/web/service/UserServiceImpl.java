package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public User show(long id) {
       return userDao.show(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    @Transactional
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        userDao.update(id,updateUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}

