package web.service;


import web.models.User;

import java.util.List;

public interface UserService {

    User show(long id);
    void saveUser(User user);

    List<User>getUserList ();
    void update(Long id, User updateUser);
    void delete(Long id);
}
