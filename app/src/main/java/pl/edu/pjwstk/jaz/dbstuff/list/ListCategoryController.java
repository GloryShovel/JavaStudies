package pl.edu.pjwstk.jaz.dbstuff.list;

import pl.edu.pjwstk.jaz.dbstuff.BranchRepository;
import pl.edu.pjwstk.jaz.dbstuff.Category;
import pl.edu.pjwstk.jaz.dbstuff.CategoryRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ListCategoryController {

    @Inject
    private CategoryRepository categoryRepository;

    public List getCategoryList(){ return categoryRepository.findAll();}


    public List getCategoriesOfBranch(Long branchId){return  categoryRepository.findCategoryByBranch(branchId);}
}
