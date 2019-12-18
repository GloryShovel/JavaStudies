package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuctionRepository {
    @PersistenceContext
    EntityManager em;

    //I didn't commented all tha is here, because everything is easy to understand

    public Optional<Auction> findAuctionById(Long id){
        var auction = em.find(Auction.class, id);
        return Optional.ofNullable(auction);
    }

    //Two or more different categories can have same auction
    public List findAuctionByTitle(Long categoryId, String tile){
        return em.createQuery("from Auction where category.id = :categoryId and title like :tile", Auction.class)
                .setParameter("categoryId", categoryId)
                .setParameter("tile", tile)
                .getResultList();
    }

    @Transactional
    public void save(Auction auction) {
        if (auction.getId() == null) {
            em.persist(auction);
        } else {
            em.merge(auction);
        }
    }

    public List findAll(){
        return em.createQuery("from Auction ", Auction.class).getResultList();
    }

}
