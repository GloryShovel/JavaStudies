package pl.edu.pjwstk.jaz.auth;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProfileRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void sampleCodeWithPC() {
        var profile = new ProfileEntity("pjanowiak");

        em.persist(profile);

        final ProfileEntity profileEntity = em.find(ProfileEntity.class, 7L);
        var list = em.createQuery("from ProfileEntity where name = :name", ProfileEntity.class)
                .setParameter("name", "pjanowiak2")
                .getResultList();
        System.out.println();
    }
}
