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

    private EditAuctionModel editAuctionModel;

    //EDIT
    //**************************************************************************************************
    public EditAuctionModel getEditRequest() {
        if (editAuctionModel == null) {
            editAuctionModel = createEditAuctionModel();
        }
        return editAuctionModel;
    }

    private EditAuctionModel createEditAuctionModel() {
        if (paramRetriever.contains("auctionId")) {
            var auctionId = paramRetriever.getLong("auctionId");
            var auction = auctionRepository.findAuctionById(auctionId).orElseThrow();
            return new EditAuctionModel(auction);
        }
        return new EditAuctionModel();
    }

    public String edit() {
        Long categoryId = getEditRequest().getCategoryId();
        Long ownerId = getEditRequest().getOwnerId();

        //Making photo list
        List<Photos> photoList = getEditRequest().getPhotos();

        //Making list for linker between auction and parameters
        List<Linker_auction_params> params = getEditRequest().getParams();

        String description = getEditRequest().getDescription();
        String title = getEditRequest().getTitle();
        float price = getEditRequest().getPrice();

        //passing mergeable object
        Category category = categoryRepository.findCategoryById(categoryId).orElseThrow();
        User owner = userRepository.findUserById(ownerId).orElseThrow();
        Auction auction = new Auction(getEditRequest().getId(), category, owner, photoList, params, title, description, price);
        auctionRepository.save(auction);

        return "/samples/myAuctionList.xhtml?faces-redirect=true";
    }

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
