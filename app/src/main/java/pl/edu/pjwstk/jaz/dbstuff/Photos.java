package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    private String link;

    public Photos(){

    }

    public Photos(String link){
        this.link = link;
    }

    public Photos(Auction auction, String link){
        this.auction = auction;
        this.link = link;
    }

    //Getters
    //**************************************************************************************************

    public Long getId() {
        return id;
    }

    public Auction getAuction() {
        return auction;
    }

    public String getLink() {
        return link;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
