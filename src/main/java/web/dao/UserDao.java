package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();

    void saveUser(User user);

    User show(long id);


    void update(Long id, User updateUser);
    void delete(Long id);
}
