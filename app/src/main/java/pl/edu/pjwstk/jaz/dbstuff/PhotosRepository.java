package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PhotosRepository {
    @PersistenceContext
    EntityManager em;

    //I didn't commented anything here, because everything is easy to understand

    public Optional<Photos> findPhotoById(Long id){
        var photo = em.find(Photos.class, id);
        return Optional.ofNullable(photo);
    }

    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public List<Photos> findAll(){
        return em.createQuery("from Photos ", Photos.class).getResultList();
    }

}
