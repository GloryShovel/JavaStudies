package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BranchRepository {
    @PersistenceContext
    EntityManager em;

    //I didn't commented all tha is here, because everything is easy to understand

    public Optional<Branch> findBranchById(Long id){
        var branch = em.find(Branch.class, id);
        return Optional.ofNullable(branch);
    }

    public List findBranchByName(String name){
        return em.createQuery("from Branch where name like :name", Branch.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Transactional
    public void save(Branch branch) {
        if (branch.getId() == null) {
            em.persist(branch);
        } else {
            em.merge(branch);
        }
    }

    public List findAll(){
        return em.createQuery("from Branch", Branch.class).getResultList();
    }

}
