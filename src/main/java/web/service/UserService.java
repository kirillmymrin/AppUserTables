package web.service;


import web.models.User;

import java.util.List;

public interface UserService {

    User getUser(long id);

    void save(User user);

    List<User> getUsers();

    void update(Long id, User updateUser);

    void delete(Long id);
}
