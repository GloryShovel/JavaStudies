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

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "auction")
    @OrderColumn(name = "order")
    private List<Photos> photos;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "auction")
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
        this.params = params;

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