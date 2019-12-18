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

    //I didn't commented anything here, becouse everything is easy to understand

    public Optional<User> findUserById(Long id){
        var user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public List findUserByName(String name){
        return em.createQuery("from User where name = :name")
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

    public List findAll(){
        return em.createQuery("from User", User.class).getResultList();
    }

}
