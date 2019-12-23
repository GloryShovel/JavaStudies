package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.*;
import pl.edu.pjwstk.jaz.servelet.ParamRetriever;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class EditAuctionController implements Serializable {
    private static final long serialVersionUID = 678934534534534534L;

    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private AuctionRepository auctionRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private ParamRetriever paramRetriever;

    private EditAuctionRequest editAuctionRequest;

    private EditAuctionModel editAuctionModel;

    //EDIT
    //**************************************************************************************************
//    public EditAuctionRequest getEditRequest() {
//        if (editAuctionRequest == null) {
//            editAuctionRequest = createEditAuctionRequest();
//        }
//        return editAuctionRequest;
//    }
//
//    private EditAuctionRequest createEditAuctionRequest() {
//        if (paramRetriever.contains("auctionId")) {
//            var auctionId = paramRetriever.getLong("auctionId");
//            var auction = auctionRepository.findAuctionById(auctionId).orElseThrow();
//            return new EditAuctionRequest(auction);
//        }
//        return new EditAuctionRequest();
//    }
//
//    public String save() {
//        Long branchId = getEditRequest().getBranchId();
//        String name = getEditRequest().getName();
//        var branch = auctionRepository.findAuctionById(branchId).orElseThrow();
//
//        //check if branch contains this category
//        if(categoryRepository.findCategoryByName(branchId, name).isEmpty()) {
//            Category category = new Category(editCategoryRequest.getId(), branch, editCategoryRequest.getName());
//            categoryRepository.save(category);
//        }else{
//            System.out.println("Nie udalo sie zedytowac kategorii");
//        }
//
//        return "/samples/categoryList.xhtml?faces-redirect=true";
//    }

    //ADD
    //**************************************************************************************************
    public EditAuctionModel getAddRequest() {
        if (editAuctionModel == null) {
            editAuctionModel = new EditAuctionModel();
        }
        return editAuctionModel;
    }

    public String add(){
        //getting values from addAuctionRequest model
        Long categoryId = getAddRequest().getCategoryId();

        //Taking owner form session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String ownerName = (String) session.getAttribute("username");
        User owner = userRepository.findUserByLogin(ownerName).get(0);

        //Making photo list
        List<Photos> photoList = new ArrayList<>();
        photoList.add(new Photos(getAddRequest().getTempPhoto0()));
        photoList.add(new Photos(getAddRequest().getTempPhoto1()));
        photoList.add(new Photos(getAddRequest().getTempPhoto2()));
        photoList.add(new Photos(getAddRequest().getTempPhoto3()));

        //Making list for linker between auction and parameters
        List<Linker_auction_params> params = new ArrayList<>();
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam0()), getAddRequest().getTempParamValue0()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam1()), getAddRequest().getTempParamValue1()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam2()), getAddRequest().getTempParamValue2()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam3()), getAddRequest().getTempParamValue3()));

        String title = getAddRequest().getTitle();
        String description = getAddRequest().getDescription();
        float price = getAddRequest().getPrice();

        //check if auction exists in list of auctions in proper category
        List<Auction> auctionList = auctionRepository.findAuctionByTitle(categoryId, title);
        if (auctionList.isEmpty()){
            Category category = categoryRepository.findCategoryById(categoryId).orElseThrow();
            Auction newAuction = new Auction(category, owner, photoList, params, title, description, price);
            auctionRepository.save(newAuction);
        }else {
            System.out.println("Nie udalo sie stworzyc aukcji");
        }

        return "/samples/auctionList.xhtml?faces-redirect=true";
    }
}
