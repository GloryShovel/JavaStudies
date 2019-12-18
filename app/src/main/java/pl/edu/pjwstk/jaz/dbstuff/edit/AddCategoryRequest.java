package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Branch;
import pl.edu.pjwstk.jaz.dbstuff.Category;

public class AddCategoryRequest {
    private String name;
    private Long branchId;

    public AddCategoryRequest() {
    }

    public AddCategoryRequest(String name, Long branchId) {
        this.name = name;
        this.branchId = branchId;
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
