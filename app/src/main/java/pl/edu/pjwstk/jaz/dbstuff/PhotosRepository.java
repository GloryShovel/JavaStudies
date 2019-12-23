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
    public void save(Photos photo) {
        if (photo.getId() == null) {
            em.persist(photo);
        } else {
            em.merge(photo);
        }
    }

    public List<Photos> findAll(){
        return em.createQuery("from Photos ", Photos.class).getResultList();
    }

}
