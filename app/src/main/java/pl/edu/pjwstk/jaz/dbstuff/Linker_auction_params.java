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
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
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

    //Getters/Setters
    //**************************************************************************************************

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Parameters getParam() {
        return param;
    }

    public void setParam(Parameters param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
