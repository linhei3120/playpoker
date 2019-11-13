package com.epam.playcard.card;

import java.util.LinkedList;

public class CardPlayer implements Runnable {

    private Integer playerIndex;

    private String playerName;

    private LinkedList<Card> selfCardList;

    private boolean getNewCard =false;

    /**
     * 构造函数
     * @param playerName
     */
    public CardPlayer(String playerName,Integer playerIndex){
        this.playerName = playerName;
        this.playerIndex = playerIndex;
    }

    public void  receiveCard(Card card){
        if(null==selfCardList){
            selfCardList = new LinkedList<>();
        }
        selfCardList.add(card);
        getNewCard= true;
    }

    @Override
    public void run() {

        int totalPoints = 0;
        StringBuilder sbCardsMsg = new StringBuilder();
        while(totalPoints<50){
            try
            {
                totalPoints = 0;
                if(null!=selfCardList && selfCardList.size()>0){

                    sbCardsMsg.setLength(0);
                    sbCardsMsg.append(playerName).append("'s card:[");

                    //compute the points
                    for(Card card :selfCardList){
                        totalPoints = totalPoints + card.getCardPoints();
                        sbCardsMsg.append(card.getCardName()).append(",");
                    }

                    //print
                    sbCardsMsg.append("]");
                    if(getNewCard){
                        print(sbCardsMsg.toString());
                        getNewCard = false;
                    }
                }
                if(!Thread.currentThread().isInterrupted()){
                    Thread.currentThread().sleep( 800 + playerIndex*5);
                }
            }
            catch(RuntimeException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ///notify the player is win.
        CardSender.getInstance().setPlayerFinished(this.playerName);
    }

    private void print(String msg){
        System.out.println(msg);
    }
}
