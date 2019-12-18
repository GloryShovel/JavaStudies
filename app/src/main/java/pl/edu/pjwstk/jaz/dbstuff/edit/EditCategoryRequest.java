package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Branch;
import pl.edu.pjwstk.jaz.dbstuff.Category;

public class EditCategoryRequest {
    private Long id;
    private String name;
    private Long branchId;

    public EditCategoryRequest() {
    }

    public EditCategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.branchId = category.getBranch().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
