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
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);

    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void update(Long id, User updateUser) {
        User userToUpd = getUser(id);
        userToUpd.setFirstName(updateUser.getFirstName());
        userToUpd.setLastName(updateUser.getLastName());
        userToUpd.setEmail(updateUser.getEmail());
        userDao.update(id, userToUpd);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }
}

