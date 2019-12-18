package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Branch;
import pl.edu.pjwstk.jaz.dbstuff.BranchRepository;
import pl.edu.pjwstk.jaz.servelet.ParamRetriever;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EditBranchController {
    @Inject
    private BranchRepository branchRepository;

    @Inject
    private ParamRetriever paramRetriever;

    private EditBranchRequest editBranchRequest;
    private AddBranchRequest addBranchRequest;

    //EDIT
    //**************************************************************************************************
    public EditBranchRequest getEditRequest() {
        if (editBranchRequest == null) {
            editBranchRequest = createEditBranchRequest();
        }
        return editBranchRequest;
    }

    private EditBranchRequest createEditBranchRequest() {
        if (paramRetriever.contains("branchId")) {
            var branchId = paramRetriever.getLong("branchId");
            var branch = branchRepository.findBranchById(branchId).orElseThrow();
            return new EditBranchRequest(branch);
        }
        return new EditBranchRequest();
    }

    public String save() {
        var branch = editBranchRequest.toBranch();
        branchRepository.save(branch);

        return "/samples/branchList.xhtml?faces-redirect=true";
    }

    //ADD
    //**************************************************************************************************
    //Create new addBranchRequest if there is none
    public AddBranchRequest getAddRequest(){
        if (addBranchRequest == null) {
            addBranchRequest = new AddBranchRequest();
        }
        return addBranchRequest;
    }

    public String add(){
        //Getting branch name from createAddBranchRequest model by getAddRequest
        String branchName = getAddRequest().getName();
        //save new branch if there is none like it else output me console info
        var branchList = branchRepository.findBranchByName(branchName);
        if (branchList.isEmpty()){
            branchRepository.save(new Branch(branchName));
        }
        else{
            System.out.println("Nie udalo sie dodac nowego branch-a");
        }
        return "/samples/branchList.xhtml?faces-redirect=true";
    }
}
