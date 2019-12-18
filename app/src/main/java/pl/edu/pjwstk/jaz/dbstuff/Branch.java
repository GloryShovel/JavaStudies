package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    public Branch(){

    }

    public Branch(String name){
        this.name = name;
    }

    public Branch(Long id, String name){
        this.id = id;
        this.name = name;
    }


    //Getters
    //**************************************************************************************************

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
