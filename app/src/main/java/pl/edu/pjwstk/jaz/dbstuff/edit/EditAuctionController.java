package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.*;
import pl.edu.pjwstk.jaz.servelet.ParamRetriever;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
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

    private AddAuctionRequest addAuctionRequest;

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
    public AddAuctionRequest getAddRequest() {
        if (addAuctionRequest == null) {
            addAuctionRequest = new AddAuctionRequest();
        }
        return addAuctionRequest;
    }

    public String add(){
        //getting values from addAuctionRequest model
        var categoryId = getAddRequest().getCategoryId();
        System.out.println("CategoryID: "+categoryId);
        var owner = userRepository.findUserById(getAddRequest().getOwnerId()).orElseThrow();

        List<Photos> photos = new ArrayList<>();
        photos.add(new Photos(getAddRequest().getTempPhoto()));
        List<Linker_auction_params> params = new ArrayList<>();
        params.add(new Linker_auction_params(new Parameters(getAddRequest().getTempParam()), getAddRequest().getTempParamValue()));

        var title = getAddRequest().getTitle();
        var description = getAddRequest().getDescription();
        var price = getAddRequest().getPrice();

        //check if auction exists in list of auctions in proper category
        var auctionList = auctionRepository.findAuctionByTitle(categoryId, title);

        if (auctionList.isEmpty()){
            var category = categoryRepository.findCategoryById(categoryId).orElseThrow();
            Auction newAuction = new Auction(category, owner, photos, params, title, description, price);
            auctionRepository.save(newAuction);
        }else {
            System.out.println("Nie udalo sie stworzyc aukcji");
        }

        return "/samples/auctionList.xhtml?faces-redirect=true";
    }
}
