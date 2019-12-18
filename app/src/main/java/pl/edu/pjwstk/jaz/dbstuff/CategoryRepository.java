package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository {
    @PersistenceContext
    EntityManager em;

    //I didn't commented anything here, because everything is easy to understand

    public Optional<Category> findCategoryById(Long id){
        var category = em.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    //Here is tricky because searching by name needs branchId and that is
    //because different branch can have the same category
    public List findCategoryByName(Long branchId, String name){
        return em.createQuery("from Category where branch.id = :branchId and  name = :name")
                .setParameter("branchId", branchId)
                .setParameter("name", name)
                .getResultList();
    }

    public List findCategoryByBranch(Long branchId){
        return em.createQuery("from Category where branch.id = :branchId")
                .setParameter("branchId", branchId)
                .getResultList();
    }

    @Transactional
    public void save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        } else {
            em.merge(category);
        }
    }

    public List findAll(){
        return em.createQuery("from Category", Category.class).getResultList();
    }

}
