package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User show(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Long id, User updateUser) {
        User userToUpd = show(id);
        userToUpd.setFirstName(updateUser.getFirstName());
        userToUpd.setLastName(updateUser.getLastName());
        userToUpd.setEmail(updateUser.getEmail());
        entityManager.persist(userToUpd);

    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("DELETE from User WHERE id =: id ").setParameter("id",id).executeUpdate();
    }
}
