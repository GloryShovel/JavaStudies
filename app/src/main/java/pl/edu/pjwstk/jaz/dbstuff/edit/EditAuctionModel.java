package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Category;
import pl.edu.pjwstk.jaz.dbstuff.Linker_auction_params;
import pl.edu.pjwstk.jaz.dbstuff.Photos;

import javax.faces.event.ValueChangeEvent;
import java.util.List;

public class EditAuctionModel {
    private Long branchId;
    private Long categoryId;
    //Needed for selecting category based on branch
    private List<Category> categoryOptions;
    private Long ownerId;

    private List<Photos> photos;
    private String tempPhoto0;
    private String tempPhoto1;
    private String tempPhoto2;
    private String tempPhoto3;

    private List<Linker_auction_params> params;
    private String tempParam0;
    private String tempParamValue0;
    private String tempParam1;
    private String tempParamValue1;
    private String tempParam2;
    private String tempParamValue2;
    private String tempParam3;
    private String tempParamValue3;

    private String title;
    private String description;
    private float price;

    public EditAuctionModel() {
    }

    public EditAuctionModel(List<Category> categories){
        this.categoryOptions = categories;
    }

    public EditAuctionModel(Long branchId, Long categoryId, Long ownerId, List<Photos> photos, List<Linker_auction_params> params, String title, String description, float price) {
        this.branchId = branchId;
        this.categoryId = categoryId;
        this.ownerId = ownerId;
        this.photos = photos;
        this.params = params;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    //Branch Category Owner
    //********************************************************************************************************
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

    public List<Category> getCategoryOptions() {
        return categoryOptions;
    }

    public void setCategoryOptions(List<Category> categoryOptions) {
        this.categoryOptions = categoryOptions;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    //Photos
    //********************************************************************************************************
    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public String getTempPhoto0() {
        return tempPhoto0;
    }

    public void setTempPhoto0(String tempPhoto0) {
        this.tempPhoto0 = tempPhoto0;
    }

    public String getTempPhoto1() {
        return tempPhoto1;
    }

    public void setTempPhoto1(String tempPhoto1) {
        this.tempPhoto1 = tempPhoto1;
    }

    public String getTempPhoto2() {
        return tempPhoto2;
    }

    public void setTempPhoto2(String tempPhoto2) {
        this.tempPhoto2 = tempPhoto2;
    }

    public String getTempPhoto3() {
        return tempPhoto3;
    }

    public void setTempPhoto3(String tempPhoto3) {
        this.tempPhoto3 = tempPhoto3;
    }

    //Params
    //********************************************************************************************************

    public List<Linker_auction_params> getParams() {
        return params;
    }

    public void setParams(List<Linker_auction_params> params) {
        this.params = params;
    }

    public String getTempParam0() {
        return tempParam0;
    }

    public void setTempParam0(String tempParam0) {
        this.tempParam0 = tempParam0;
    }

    public String getTempParamValue0() {
        return tempParamValue0;
    }

    public void setTempParamValue0(String tempParamValue0) {
        this.tempParamValue0 = tempParamValue0;
    }

    public String getTempParam1() {
        return tempParam1;
    }

    public void setTempParam1(String tempParam1) {
        this.tempParam1 = tempParam1;
    }

    public String getTempParamValue1() {
        return tempParamValue1;
    }

    public void setTempParamValue1(String tempParamValue1) {
        this.tempParamValue1 = tempParamValue1;
    }

    public String getTempParam2() {
        return tempParam2;
    }

    public void setTempParam2(String tempParam2) {
        this.tempParam2 = tempParam2;
    }

    public String getTempParamValue2() {
        return tempParamValue2;
    }

    public void setTempParamValue2(String tempParamValue2) {
        this.tempParamValue2 = tempParamValue2;
    }

    public String getTempParam3() {
        return tempParam3;
    }

    public void setTempParam3(String tempParam3) {
        this.tempParam3 = tempParam3;
    }

    public String getTempParamValue3() {
        return tempParamValue3;
    }

    public void setTempParamValue3(String tempParamValue3) {
        this.tempParamValue3 = tempParamValue3;
    }

    //Title Description Price
    //********************************************************************************************************
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
