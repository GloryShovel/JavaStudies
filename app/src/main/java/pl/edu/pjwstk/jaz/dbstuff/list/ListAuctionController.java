package pl.edu.pjwstk.jaz.dbstuff.list;

import pl.edu.pjwstk.jaz.dbstuff.AuctionRepository;
import pl.edu.pjwstk.jaz.dbstuff.BranchRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ListAuctionController {

    @Inject
    private AuctionRepository auctionRepository;

    public List getAuctionList(){ return auctionRepository.findAll();}
}
