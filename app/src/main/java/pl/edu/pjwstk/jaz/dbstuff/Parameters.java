package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
public class Parameters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String param;

    public Parameters(){

    }

    public Parameters(String param){
        this.param = param;
    }

    //Getters
    //**************************************************************************************************

    public Long getId() {
        return id;
    }

    public String getParam() {
        return param;
    }
}
