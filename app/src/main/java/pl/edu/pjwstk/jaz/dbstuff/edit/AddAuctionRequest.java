package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Linker_auction_params;

import javax.faces.event.ValueChangeEvent;
import java.util.List;

public class AddAuctionRequest {
    private Long branchId;
    private Long categoryId;
    private Long ownerId;

    private List photos;
    private String tempPhoto;
    private List<Linker_auction_params> params;
    private String tempParam;
    private String tempParamValue;

    private String title;
    private String description;
    private float price;

    public AddAuctionRequest() {
    }


    public AddAuctionRequest(Long branchId, Long categoryId, Long ownerId, List photos, List<Linker_auction_params> params, String title, String description, float price) {
        this.branchId = branchId;
        this.categoryId = categoryId;
        this.ownerId = ownerId;
        this.photos = photos;
        this.params = params;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void branchIdChange(ValueChangeEvent event){
        setBranchId(Long.parseLong(event.getNewValue().toString()));
    }

    public void addToPhotos(ValueChangeEvent event){
        System.out.println(event.getNewValue().toString());
//        List tempList = getParams();
//        tempList.add(event.getNewValue().toString());
    }

    public void addToParams(ValueChangeEvent event){
        System.out.println(event.getNewValue().toString());
//        List tempList = getParams();
//        tempList.add(event.getNewValue().toString());
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List getPhotos() {
        return photos;
    }

    public void setPhotos(List photos) {
        this.photos = photos;
    }

    public String getTempPhoto() {
        return tempPhoto;
    }

    public void setTempPhoto(String tempPhoto) {
        this.tempPhoto = tempPhoto;
    }

    public List<Linker_auction_params> getParams() {
        return params;
    }

    public void setParams(List<Linker_auction_params> params) {
        this.params = params;
    }

    public String getTempParam() {
        return tempParam;
    }

    public void setTempParam(String tempParam) {
        this.tempParam = tempParam;
    }

    public String getTempParamValue() {
        return tempParamValue;
    }

    public void setTempParamValue(String tempParamValue) {
        this.tempParamValue = tempParamValue;
    }

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
