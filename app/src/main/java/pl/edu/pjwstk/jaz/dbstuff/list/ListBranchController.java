package pl.edu.pjwstk.jaz.dbstuff.list;

import pl.edu.pjwstk.jaz.dbstuff.BranchRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ListBranchController {

    @Inject
    private BranchRepository branchRepository;

    public List getBranchList(){ return branchRepository.findAll();}
}
