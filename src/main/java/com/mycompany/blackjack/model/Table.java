/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author jakeh
 */
@Startup
@Singleton
public class Table implements Runnable{

    private Deck deck;

    private String tableName;

    private List<String> gameMessages = new LinkedList();

    private static List<Seat> seats;
    private List<Card> dealerCards = new LinkedList();

    private int dealerNumber;

    private boolean currentlyPlaying = false;
    
    private int numberOfPlayers;

    @PostConstruct
    public void init() {
        deck=new Deck();
        seats = new LinkedList();
        for (int x = 0; x < 9; x++) {
            seats.add(new Seat());
        }
    }
    
    @Override
    public void run() {
        
    }

    private void reset() {
        dealerNumber = 0;

        dealerCards.clear();
    }

    public void start() {
        reset();
        currentlyPlaying = true;
        deck.shuffle();

        for (Seat seat : seats) {
            if (seat.getHand() != null) {
                seat.getHand().reset();
            }
            if (seat.getPlayer() != null) {
                seat.getHand().addCard(deck.drawCard());
            }

        }
        dealerCards.add(deck.drawCard());
        for (Seat seat : seats) {
            seat.getHand().addCard(deck.drawCard());
        }

        setDealerNumber();
    }

    private void startTurns() throws InterruptedException {
        for (Seat seat : seats) {
            if (seat.getHand().isBust()) {
                continue;
            } else if (seat.getHand().isStand()) {
                continue;
            }
            seat.getHand().setPlayersTurn(true);
            while (seat.getHand().getAction() == null) {
                Thread.sleep(1500);
            }
            doAction(seat.getHand());
        }
    }

    private void endOfAction() {
        for (Seat seat : seats) {
            if (seat.getHand().isBust()) {
                continue;
            }

            while (dealerNumber < 16 && dealerNumber < 21) {
                dealerCards.add(deck.drawCard());
                setDealerNumber();
            }
            if (dealerNumber < 21) {
                if (seat.getHand().getPlayerNumber() > dealerNumber) {
                    seat.getHand().addChips(seat.getHand().getBet() * 2);
                    gameMessages.add("You have won: " + seat.getHand().getPlayer().getUserName());
                } else if (seat.getHand().getPlayerNumber() < dealerNumber) {
                    gameMessages.add("You have Lost." + seat.getHand().getPlayer().getUserName());
                } else {
                    gameMessages.add("It's a tie." + seat.getHand().getPlayer().getUserName());
                }
            } else {
                seat.getHand().addChips(seat.getHand().getBet() * 2);
                gameMessages.add("You have won: " + seat.getHand().getPlayer().getUserName());
            }
        }
    }

    private void doAction(Hand hand) {
        switch (hand.getAction()) {
            case STAND:
                actionStand(hand);
                break;
            case HIT:
                actionHit(hand);
                break;
            case SPLIT:
                actionSplit(hand);
                break;
            case DOUBLE:
                actionDouble(hand);
                break;
        }
    }

    private void actionHit(Hand hand) {
        hand.addCard(deck.drawCard());

        if (hand.getPlayerNumber() > 21) {
            gameMessages.add(hand.getPlayer().getUserName() + "You have Bust.");
        }
    }

    private void actionStand(Hand hand) {
        hand.setStand();
    }

    private void actionDouble(Hand hand) {
        if (hand.canDouble()) {
            hand.addCard(deck.drawCard());
            hand.setStand();
        }
    }

    private void actionSplit(Hand hand) {

    }

    private void setDealerNumber() {
        int num = 0;
        int numberOfAces = 0;
        for (Card card : dealerCards) {
            if (card.getValue() != 0) {
                num += card.getValue();

            } else {
                num += 11;
                numberOfAces++;
            }
        }

        for (int x = 0; x < numberOfAces; x++) {
            if (num > 21) {
                num -= 10;
            }
        }
        dealerNumber = num;
    }

    public void split() {

    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public List<String> getGameMessages() {
        return gameMessages;
    }

    public int getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(int dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public boolean isCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void joinSeat(Seat seat, Player player) {
        if (seat.getPlayer() == null) {
            seat.setPlayer(player);
        }
    }

    public void leaveSeat(Seat seat) {
        seat.setPlayer(null);
    }

    public void addGameMessage(String message) {
        System.out.println("adding game message" + message);
        gameMessages.add(message);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    

}
