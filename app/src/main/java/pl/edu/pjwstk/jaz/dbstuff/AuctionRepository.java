package pl.edu.pjwstk.jaz.dbstuff;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuctionRepository {
    @PersistenceContext
    EntityManager em;
    @Inject
    private UserRepository userRepository;

    //I didn't commented all tha is here, because everything is easy to understand

    public Optional<Auction> findAuctionById(Long id){
        Auction auction = em.createQuery("select distinct a from Auction a left join fetch a.photos where a.id = :id", Auction.class)
                .setParameter("id", id)
                .getSingleResult();

        return Optional.ofNullable(auction);
    }

    //Two or more different categories can have same auction
    public List<Auction> findAuctionByTitle(Long categoryId, String tile){
        return em.createQuery("select distinct a from Auction a left join fetch a.photos where a.category.id = :categoryId and a.title like :tile", Auction.class)
                .setParameter("categoryId", categoryId)
                .setParameter("tile", tile)
                .getResultList();
    }

    public List<Auction> findAuctionByOwner(){
        //Taking owner form session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String ownerLogin = (String) session.getAttribute("username");

        //Splited just for debugging
        List<Auction> result = em.createQuery("select distinct a from Auction a left join fetch a.photos where a.owner.login = :ownerLogin", Auction.class)
                .setParameter("ownerLogin", ownerLogin)
                .getResultList();
        return result;
    }

    @Transactional
    public void save(Auction auction) {
        if (auction.getId() == null) {
            em.persist(auction);
        } else {
            em.merge(auction);
        }
    }

    public List<Auction> findAll(){
        //Splited just for debugging
        List<Auction> result =  em.createQuery("select distinct a from Auction a left join fetch a.photos", Auction.class)
                .getResultList();
        return result;
    }

}
