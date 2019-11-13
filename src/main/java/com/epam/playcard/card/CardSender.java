package com.epam.playcard.card;

import java.util.*;

/**
 * card sender class
 */
public class CardSender extends Thread {

    /**
     * rounder wait interval
     */
    private static  final Integer ROUNDER_WAIT_TIME = 3000;

    /**
     * poker list
     */
    private LinkedList<Card> pokerList;

    /**
     * the flag for one player has win
     */
    protected static boolean playerFinished = false;

    /**
     * winner player
     */
    protected static String winner = "";

    private List<CardPlayer>  playerList;


    /**
     * private construct
     */
    private CardSender(){}

    private static class CardSenderFactory{
        private static CardSender instance = new CardSender();
    }

    public static CardSender getInstance(){
        return CardSenderFactory.instance;
    }

    public void setPlayerList(List<CardPlayer>  playerList){
        this.playerList = playerList;
    }

    public void getPokerList(){

        if(null==pokerList){
            Poker pokers =new Poker();
            pokers.createPoker();
            pokerList = pokers.getPokerList();
        }
    }

    public void shufflePokers(){
            Collections.shuffle(pokerList);
    }

    public void setPlayerFinished(String playerName){
        playerFinished = true;
        winner = playerName;
    }

    private void print(String msg){
        System.out.println(msg);
    }

    /**
     * print the pokers
     */
    public void printPokers(){
        print("-------------------------");
        print("pokers size:"+pokerList.size());
        for(Card card:pokerList){
            System.out.print(card.getCardName()+";");
        }
        print(" ");
    }

    public void run(){

        print("poker game start...");

        List<Thread> threadList = new ArrayList<Thread>();
        try {
            ///start player sub Thread
            for(int i=0;i<playerList.size();i++){
                Thread subTread = new Thread(playerList.get(i));
                threadList.add( subTread);
                subTread.start();
            }

            ///get new pokers
            this.getPokerList();
            printPokers();

            ///shuffle pokers
            this.shufflePokers();
            printPokers();

            int iRoundIndex =1;

            while(!playerFinished){
                print("--------------------");
                print("send card,Rounder" + iRoundIndex + ":");
                for(int i =0;i<playerList.size();i++){
                    Card card = pokerList.get(i);
                    playerList.get(i).receiveCard(card);
                    pokerList.remove(i);
                    System.out.print("player" + (i+1) + ":"+ card.getCardName() +";");
                }
                print(" ");
                print("--------------------");
                Thread.sleep(ROUNDER_WAIT_TIME);

                iRoundIndex++;
            }

            print("Winner is :"  + winner);

            ///stop the sub thread
            for(Thread subThread: threadList){
                subThread.stop();
            }

            ///interrupt main thread
            this.interrupt();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
