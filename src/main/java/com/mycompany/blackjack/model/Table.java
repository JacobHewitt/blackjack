/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Table implements Runnable {

    private Deck deck;

    private String tableName;

    private List<GameMessage> gameMessages = new LinkedList();

    private List<Seat> seats;
    private List<Card> dealerCards = new LinkedList();

    private int dealerNumber;

    private boolean currentlyPlaying = false;

    private Hand currentHand;

    private int numberOfPlayers;

    private int timer;

    public Table() {
        System.out.println("adding seats INIT TABLE");
        deck = new Deck();
        seats = new LinkedList();
        for (int x = 0; x < 9; x++) {
            seats.add(new Seat(x));
        }

    }

    @Override
    public void run() {
        waitForPlayers();
    }
    
    private void waitForPlayers(){
        while (true) {
            timer = 0;
            while(timer < 10){
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
                timer++;
            }
            if (numberOfPlayers > 0) {
                System.out.println("STARTING GAME");
                
                startGame();
            }
        }
    }

    private void reset() {
        timer = 0;
        dealerNumber = 0;
        dealerCards.clear();
        currentHand=null;
        for (Seat seat : seats) {
            seat.resetHands();
        }
    }

    public void startGame() {
        addGameMessage("Game", "STARTING NEW HAND");
        reset();
        currentlyPlaying = true;
        deck.shuffle();

        for (Seat seat : seats) {
            if (seat.getPlayer() != null) {
                Hand toAdd = new Hand(seat.getPlayer(), seat.getBet());
                toAdd.addCard(deck.drawCard());
                seat.addHand(toAdd);
            }

        }
        dealerCards.add(deck.drawCard());
        for (Seat seat : seats) {
            if (seat.getPlayer() != null) {
                seat.getHands().get(0).addCard(deck.drawCard());
            }

        }

        setDealerNumber();
        startTurns();
    }

    private void startTurns() {
        for (Seat seat : seats) {
            if (seat.getPlayer() == null) {
                continue;
            }

            seatsTurn(seat);

        }
        endOfAction();
    }

    private void seatsTurn(Seat seat) {
        List<Hand> hands = seat.getHands();
        addGameMessage("Game", seat.getPlayer().getFirstName()+" it is your turn. You have 10 seconds to act.");
        for (Hand hand : hands) {
            timer = 0;
            if (hand.isBust()) {
                continue;
            } else if (hand.isStand()) {
                continue;
            }
            hand.setPlayersTurn(true);
            currentHand = hand;
            while (hand.getAction() == null && timer <= 10) {
                try {
                    System.out.println("WAITING PLAYERS TURN");
                    Thread.sleep(500);
                    timer++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            doAction(hand);
            currentHand = null;
        }

    }

    private void endOfAction() {
        while (dealerNumber < 16 && dealerNumber < 21) {
            dealerCards.add(deck.drawCard());
            setDealerNumber();
        }

        for (Seat seat : seats) {
            for (Hand hand : seat.getHands()) {
                if (hand.isBust()) {
                    continue;
                }

                if (dealerNumber < 21) {
                    if (hand.getPlayerNumber() > dealerNumber) {
                        seat.getPlayer().addChips(hand.getBet() * 2);
                        addGameMessage("Game", "You have won: " + hand.getPlayer().getFirstName());
                    } else if (hand.getPlayerNumber() < dealerNumber) {
                        addGameMessage("Game", "You have Lost." + hand.getPlayer().getFirstName());
                    } else {
                        addGameMessage("Game", "It's a tie." + hand.getPlayer().getFirstName());
                    }
                } else {
                    seat.getPlayer().addChips(hand.getBet() * 2);
                    addGameMessage("Game", "You have won: " + hand.getPlayer().getFirstName());
                }
            }

        }
        currentlyPlaying = false;
    }

    private void doAction(Hand hand) {
        if (hand.getAction() == null) {
            hand.setAction(Action.STAND);
        }
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
        addGameMessage("Game", hand.getPlayer().getFirstName()+" has "+hand.getAction());
    }

    private void actionHit(Hand hand) {
        hand.addCard(deck.drawCard());

        if (hand.getPlayerNumber() > 21) {
            addGameMessage("Game", hand.getPlayer().getFirstName() + "You have Bust.");
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

    public List<GameMessage> getGameMessages() {
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

    public void joinSeat(int seatNumber, Player player) {
        Seat seat = seats.get(seatNumber);
        if (seat.getPlayer() == null) {
            seat.setPlayer(player);
            addGameMessage("Game", player.getFirstName() + " has joined seat: " + seatNumber);
            numberOfPlayers++;
        }
    }

    public void leaveSeat(Seat seat) {
        addGameMessage("Game", seat.getPlayer().getFirstName()+" has left seat: "+seat.getSeatNumber());
        seat.setPlayer(null);
        numberOfPlayers--;
    }

    public void addGameMessage(String author, String comment) {
        gameMessages.add(new GameMessage(author, comment));
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

    public Hand getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    public boolean canStart() {
        if (currentlyPlaying == false) {
            return true;
        }
        return false;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    
    

}
