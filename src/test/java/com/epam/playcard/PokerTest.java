package com.epam.playcard;


import com.epam.playcard.card.CardPlayer;
import com.epam.playcard.card.CardSender;
import com.epam.playcard.card.Poker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PokerTest {


    /**
     * case 1 create poker
     */
    @Test
    public void PokerCreate(){
        Poker poker =new Poker();
        poker.createPoker();
        poker.print();

        poker.shufflePokers();
        poker.print();
    }

    /**
     * case 2  shuffle pokers
     */
    @Test
    public void shufflePokers(){
        Poker poker =new Poker();
        poker.createPoker();
        poker.shufflePokers();
        poker.print();
    }

    /**
     * case 3  test send card
     */
    @Test
    public void sendCard(){
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
