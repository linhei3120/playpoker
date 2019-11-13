package com.epam.playcard.card;

import java.util.ArrayList;
import java.util.List;

public class PokerTest {

    public static void main(String[] args) {

        CardPlayer player1 = new CardPlayer("player1",1);
        CardPlayer player2 = new CardPlayer("player2",2);
        CardPlayer player3 = new CardPlayer("player3",3);
        List<CardPlayer> playList= new ArrayList<>();
        playList.add(player1);
        playList.add(player2);
        playList.add(player3);
        CardSender sender = CardSender.getInstance();
        sender.setPlayerList(playList);
        sender.start();
    }
}
