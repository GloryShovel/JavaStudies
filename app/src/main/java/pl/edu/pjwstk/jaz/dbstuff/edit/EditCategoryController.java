package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Branch;
import pl.edu.pjwstk.jaz.dbstuff.BranchRepository;
import pl.edu.pjwstk.jaz.dbstuff.Category;
import pl.edu.pjwstk.jaz.dbstuff.CategoryRepository;
import pl.edu.pjwstk.jaz.servelet.ParamRetriever;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.plaf.metal.MetalDesktopIconUI;

@Named
@RequestScoped
public class EditCategoryController {
    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private BranchRepository branchRepository;
    @Inject
    private ParamRetriever paramRetriever;

    private EditCategoryRequest editCategoryRequest;

    private AddCategoryRequest addCategoryRequest;

    //EDIT
    //**************************************************************************************************
    public EditCategoryRequest getEditRequest() {
        if (editCategoryRequest == null) {
            editCategoryRequest = createEditCategoryRequest();
        }
        return editCategoryRequest;
    }

    private EditCategoryRequest createEditCategoryRequest() {
        if (paramRetriever.contains("categoryId")) {
            var categoryId = paramRetriever.getLong("categoryId");
            var category = categoryRepository.findCategoryById(categoryId).orElseThrow();
            return new EditCategoryRequest(category);
        }
        return new EditCategoryRequest();
    }

    public String save() {
        Long branchId = getEditRequest().getBranchId();
        String name = getEditRequest().getName();
        var branch = branchRepository.findBranchById(branchId).orElseThrow();

        //check if branch contains this category
        if(categoryRepository.findCategoryByName(branchId, name).isEmpty()) {
            Category category = new Category(editCategoryRequest.getId(), branch, editCategoryRequest.getName());
            categoryRepository.save(category);
        }else{
            System.out.println("Nie udalo sie zedytowac kategorii");
        }

        return "/samples/categoryList.xhtml?faces-redirect=true";
    }

    //ADD
    //**************************************************************************************************
    public AddCategoryRequest getAddRequest() {
        if (addCategoryRequest == null) {
            addCategoryRequest = new AddCategoryRequest();
        }
        return addCategoryRequest;
    }

    public String add(){
        //getting values for query from addCategoryRequest model
        var branchId = getAddRequest().getBranchId();
        var name = getAddRequest().getName();
        //check if category exists in list of categories in proper branch
        var categoryList = categoryRepository.findCategoryByName(branchId, name);

        if (categoryList.isEmpty()){
            var branch = branchRepository.findBranchById(branchId).orElseThrow();
            Category newCategory = new Category(branch, name);
            categoryRepository.save(newCategory);
        }else {
            System.out.println("Nie udalo sie stworzyc kategorii");
        }

        return "/samples/categoryList.xhtml?faces-redirect=true";
    }
}
