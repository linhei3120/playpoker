package com.epam.playcard.card;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;


/**
 * poker class
 */
public class Poker implements Serializable {

    private static final long serialVersionUID = 1L;

    private final static String[] JOKES ={"black Joke","red Joke" };
    private final static Integer JOKE_POINTS = 20;


    private LinkedList<Card> pokerList;


    /**
     * get the pokers size
     * @return
     */
    public int getSize() {
        return pokerList.size();
    }


    /**
     * get pokers
     * @return
     */
    public LinkedList getPokerList() {
        return pokerList;
    }

    /**
     * creator the pokers
     * @return
     */
    public void createPoker(){

        LinkedList pokerList=new LinkedList();
        String[] arrCards = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Integer[] arrPoints = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        ///add normal cards
        for(int i=0;i<arrCards.length;i++){
            for(int j=0;j<4;j++){
                pokerList.add(new Card(arrCards[i],arrPoints[i]));
            }
        }

        ///add jokers
        for(int i = 0;i<JOKES.length; i++){
            pokerList.add(new Card(JOKES[i],JOKE_POINTS));
        }
        this.pokerList =  pokerList;
    }


    /**
     * shuffle pokers
     */
    public void shufflePokers(){
        Collections.shuffle(pokerList);
    }

    /**
     * print the pokers
     */
    public void print(){
        System.out.println("-------------------------");
        System.out.println("pokers size:"+pokerList.size());
        for(Card card:pokerList){
            System.out.print(card.getCardName()+";");
        }
        System.out.println(" ");
        System.out.println("-------------------------");
    }
}
