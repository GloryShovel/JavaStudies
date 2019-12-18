package pl.edu.pjwstk.jaz.dbstuff;


import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    private String name;

    public Category(){

    }

    public Category(Branch branch, String name){
        this.branch = branch;
        this.name = name;
    }

    public Category(Long id, Branch branch, String name){
        this.id = id;
        this.branch = branch;
        this.name = name;
    }

    //Getters
    //**************************************************************************************************
    public Long getId() {
        return id;
    }

    public Branch getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }
}
