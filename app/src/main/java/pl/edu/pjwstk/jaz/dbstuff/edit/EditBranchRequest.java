package pl.edu.pjwstk.jaz.dbstuff.edit;

import pl.edu.pjwstk.jaz.dbstuff.Branch;

public class EditBranchRequest {
    private Long id;
    private String name;

    public EditBranchRequest() {
    }

    public EditBranchRequest(Branch branch) {
        this.id = branch.getId();
        this.name = branch.getName();
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

    public Branch toBranch() {
        return new Branch(id, name);
    }
}
