package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void save(User user);

    User getUser(long id);


    void update(Long id, User updateUser);

    void delete(Long id);
}
