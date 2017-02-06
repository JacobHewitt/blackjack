/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author jakeh
 */
@SessionScoped
@ManagedBean
public class SinglePlayerTable {

    private Deck deck;

    private List<GameMessage> gameMessages = new LinkedList();

    @Inject
    private Player player;
    
    private Seat playerInSeat;
    
    
    private List<Card> dealerCards = new LinkedList();

    private int dealerNumber;

    private boolean currentlyPlaying = false;

    private Hand currentHand;

    
    public SinglePlayerTable() {
        deck = new Deck();

    }
    
    @PostConstruct
    public void init(){
        playerInSeat = new Seat(0);
        playerInSeat.setPlayer(player);
    }

    private void reset() {
        dealerNumber = 0;
        dealerCards.clear();
        currentHand = null;
        playerInSeat.resetHands();
    }

    public void startGame() {
        addGameMessage("Game", "STARTING NEW HAND");
        reset();
        currentlyPlaying = true;
        deck.shuffle();

        Hand toAdd = new Hand(playerInSeat.getPlayer());
        toAdd.addCard(deck.drawCard());
        playerInSeat.addHand(toAdd);

        dealerCards.add(deck.drawCard());
        playerInSeat.getHands().get(0).addCard(deck.drawCard());

        setDealerNumber();

        currentHand = playerInSeat.getHands().get(0);
        
    }

    private void endOfAction() {
        while (dealerNumber < 16 && dealerNumber < 21) {
            dealerCards.add(deck.drawCard());
            setDealerNumber();
        }

        for (Hand hand : playerInSeat.getHands()) {
            if (hand.isBust()) {
                continue;
            }

            if (dealerNumber < 21) {
                if (hand.getPlayerNumber() > dealerNumber) {
                    playerInSeat.getPlayer().addChips(hand.getBet() * 2);
                    addGameMessage("Game", "You have won: " + hand.getPlayer().getUserName());
                } else if (hand.getPlayerNumber() < dealerNumber) {
                    addGameMessage("Game", "You have Lost." + hand.getPlayer().getUserName());
                } else {
                    addGameMessage("Game", "It's a tie." + hand.getPlayer().getUserName());
                }
            } else {
                playerInSeat.getPlayer().addChips(hand.getBet() * 2);
                addGameMessage("Game", "You have won: " + hand.getPlayer().getUserName());
            }
        }

        currentlyPlaying = false;
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

    public void addGameMessage(String author, String comment) {
        gameMessages.add(new GameMessage(author, comment));
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

    public Seat getPlayer() {
        return playerInSeat;
    }

    public void setPlayer(Seat player) {
        this.playerInSeat = player;
    }
    
    public boolean canSplit(){
        if(currentHand == null)return false;
        if(currentHand.getPlayerCards().size() == 2){
            if(currentHand.getPlayerCards().get(0).getRank().equals(currentHand.getPlayerCards().get(1).getRank())){
                return true;
            }
        }
        return false;
    }
    
    public boolean canDouble(){
        if(currentHand == null)return false;
        if(currentHand.getPlayerCards().size() == 2)return true;
        return false;
    }
    
    public void hit(){
        if(currentHand != null){
            if(currentHand.isStand() == false){
                currentHand.addCard(deck.drawCard());
                actionCompleted();
            }
            
        }
    }
    
    private void actionCompleted(){
        if(currentHand!=null){
            if(currentHand.isBust()){
                findNextHand();
            }else if(currentHand.isStand()){
                findNextHand();
            }else{
                
            }
        }
    }
    
    public void stand(){
        if(currentHand != null){
            currentHand.setStand();
            findNextHand();
        }
    }
    
    public void doDouble(){
        if(currentHand != null){
            currentHand.addCard(deck.drawCard());
            currentHand.setStand();
            findNextHand();
        }
    }
    
    public void doSplit(){
        if(currentHand!=null){
            Card card1 = currentHand.getPlayerCards().get(0);
            Card card2 = currentHand.getPlayerCards().get(1);
            
            Hand newHand1 = new Hand(playerInSeat.getPlayer());
            Hand newHand2 = new Hand(playerInSeat.getPlayer());
            
            newHand1.addCard(card1);
            newHand2.addCard(card2);
            
            List<Hand> playerHands = playerInSeat.getHands();
            playerHands.remove(currentHand);
            
            playerHands.add(newHand1);
            playerHands.add(newHand2);
            
            playerInSeat.setHands(playerHands);
            
            
        }
    }
    
    public boolean canHit(){
        if(currentHand!= null){
            if(currentHand.isBust() != true && currentHand.isStand() != true){
                return true;
            }
        }
        return false;
    }
    
    public boolean canStand(){
        if(currentHand!=null){
            if(currentHand.isBust() != true && currentHand.isStand()!=true){
                return true;
            }
        }
        
        return false;
    }
    
    private void findNextHand(){
        currentHand = null;
        List<Hand> playerHands = playerInSeat.getHands();
        for(Hand hand : playerHands){
            if(hand.isBust() || hand.isStand()){
                continue;
            }else{
                currentHand = hand;
                break;
            }
            
        }
        if(currentHand == null){
            endOfAction();
        }
    }

}
