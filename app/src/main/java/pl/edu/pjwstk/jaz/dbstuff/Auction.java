package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "auction")
    @OrderColumn(name = "order_by")
    private List<Photos> photos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "auction", fetch = FetchType.EAGER)
    private List<Linker_auction_params> params;



    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;

    public Auction(){

    }

    public Auction(Category category, User owner, List<Photos> photos, List<Linker_auction_params> params, String title, String description, float price){
        this.category = category;
        this.owner = owner;
        this.photos = photos;
        for (Photos photo: photos) {
            photo.setAuction(this);
        }
        this.params = params;
        for (Linker_auction_params param: params) {
            param.setAuction(this);
        }

        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Auction(Long id, Category category, User owner, List<Photos> photos, List<Linker_auction_params> params, String title, String description, float price){
        this.id = id;
        this.category = category;
        this.owner = owner;
        this.photos = photos;
        for (Photos photo: photos) {
            photo.setAuction(this);
        }
        this.params = params;
        for (Linker_auction_params param: params) {
            param.setAuction(this);
        }

        this.title = title;
        this.description = description;
        this.price = price;
    }

    //Getters
    //**************************************************************************************************

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public User getOwner(){return owner;}

    public List<Photos> getPhotos() {
        return photos;
    }

    public List<Linker_auction_params> getParams() {
        return params;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
}
