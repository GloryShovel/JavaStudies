package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    //I didn't commented anything here, because everything is easy to understand

    public Optional<User> findUserById(Long id){
        var user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public List<User> findUserByLogin(String login){
        return em.createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
    }

    public List<User> findUserByName(String name){
        return em.createQuery("from User where name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public List<User> findAll(){
        return em.createQuery("from User", User.class).getResultList();
    }

}
