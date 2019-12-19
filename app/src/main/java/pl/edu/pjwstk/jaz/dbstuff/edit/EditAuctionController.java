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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class EditAuctionController {
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
            //TODO: zrobic nowy addAuctionRequest z pustym list category i pozniej dodawac liste category kiedy branchId zostanie ustawione


            //Sprawdzic czy branchID jest ustawione, jesli jest to pobrac liste kategorii
            //i zapisac to w modelu

//            if(getAddRequest().getBranchId() != null){
//                List<Category> categoryList = categoryRepository.findCategoryByBranch(getAddRequest().getBranchId());
//
//                editAuctionModel = new EditAuctionModel(categoryList);
//            }

            editAuctionModel = new EditAuctionModel(Collections.emptyList());
        }
        return editAuctionModel;
    }

    public String add(){
        //TODO: mozliwe ze trzeba w tej metodzie dodac zapisywanie do photos i parameters, a dopiero potem dodawac do auctona czy linkera
        //getting values from addAuctionRequest model
        Long categoryId = getAddRequest().getCategoryId();
        //Taking owner form session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String ownerName = (String) session.getAttribute("username");
        User owner = userRepository.findUserByName(ownerName).get(0);
        //Making photo list
        List<Photos> photoList = new ArrayList<>();
        //TODO: zapytaj jak zrobic zapisywanie zdjec i paramaetrow, zeby wszystko poszlo samoistnie
        // (zeby zrobic auction poczeba zdjec, zeby zrobic zdjecia poczeba auctiona)
        photoList.add(new Photos(getAddRequest().getTempPhoto0()));
        photoList.add(new Photos(getAddRequest().getTempPhoto1()));
        photoList.add(new Photos(getAddRequest().getTempPhoto2()));
        photoList.add(new Photos(getAddRequest().getTempPhoto3()));
        //Making list for linker between auction and parameters
        List<Linker_auction_params> params = new ArrayList<>();
        //TODO: tu tez
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam0()), getAddRequest().getTempParamValue0()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam1()), getAddRequest().getTempParamValue1()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam2()), getAddRequest().getTempParamValue2()));
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam3()), getAddRequest().getTempParamValue3()));

        var title = getAddRequest().getTitle();
        var description = getAddRequest().getDescription();
        var price = getAddRequest().getPrice();

        //check if auction exists in list of auctions in proper category
        var auctionList = auctionRepository.findAuctionByTitle(categoryId, title);

        if (auctionList.isEmpty()){
            var category = categoryRepository.findCategoryById(categoryId).orElseThrow();
            Auction newAuction = new Auction(category, owner, photoList, params, title, description, price);
            auctionRepository.save(newAuction);
        }else {
            System.out.println("Nie udalo sie stworzyc aukcji");
        }

        return "/samples/auctionList.xhtml?faces-redirect=true";
    }
}
