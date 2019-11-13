package com.epam.playcard.card;

import java.io.Serializable;


/**
 * card class
 */
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * card name
     */
    private String cardName;
    /**
     * card points
     */
    private Integer cardPoints;// points

    public Card ( String cardName,Integer cardPoints){
        this.cardName= cardName;
        this.cardPoints = cardPoints;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardPoints() {
        return cardPoints;
    }

    public void setCardPoints(Integer cardPoints) {
        this.cardPoints = cardPoints;
    }
}
