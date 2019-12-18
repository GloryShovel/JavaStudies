package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Auction;
import pl.edu.pjwstk.jaz.dbstuff.Category;

import java.util.List;

public class EditAuctionRequest {
    private Long id;
    private Long categoryId;
//    private List photos;
//    private List params;

    private String title;
    private String description;
    private float price;

    public EditAuctionRequest() {
    }

    public EditAuctionRequest(Auction auction) {
        this.id = auction.getId();
        this.categoryId = auction.getCategory().getId();
//        this.photos = auction.getPhotos();
//        this.params = auction.getParams();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.price = auction.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

//    public List getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List photos) {
//        this.photos = photos;
//    }
//
//        public List getParams() {
//        return params;
//    }
//
//    public void setParams(List params) {
//        this.params = params;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
