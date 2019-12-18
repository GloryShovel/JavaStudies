package pl.edu.pjwstk.jaz.dbstuff.edit;

public class AddBranchRequest {
    private String name;

    public AddBranchRequest() {
    }

    public AddBranchRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
