package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "linker_auction_params")
public class Linker_auction_params implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "parameter_id")
    private Parameters param;

    private String value;

    public Linker_auction_params(){

    }

    public Linker_auction_params(Parameters param, String value){
        this.param = param;
        this.value = value;
    }

    public Linker_auction_params(Auction auction, Parameters param, String value){
        this.auction = auction;
        this.param = param;
        this.value = value;
    }

    //Getters
    //**************************************************************************************************

    public Auction getAuction() {
        return auction;
    }

    public Parameters getParam() {
        return param;
    }
    public String getValue(){return value;}
}
