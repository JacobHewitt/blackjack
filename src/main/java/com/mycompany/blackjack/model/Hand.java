/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import com.mycompany.blackjack.entitys.Player;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jakeh
 */
public class Hand {

    private Player player;

    private int bet;

    private int playerNumber;

    private List<Card> playerCards = new LinkedList<Card>();

    private boolean stand = false;
    
    private boolean playersTurn = false;
    
    private boolean canDouble = true;
    
    private Action action;
    
    public Hand(Player player, int bet) {
        this.player = player;
        this.bet = bet;
    }

    public void addCard(Card card) {
        playerCards.add(card);
        setPlayerNumber();
    }
    
    public int getPlayerNumber(){
        return playerNumber;
    }
    
    public void setPlayersTurn(boolean boo){
        playersTurn=boo;
    }
    
    public boolean getPlayersTurn(){
        return playersTurn;
    }
    
    public void setAction(Action action){
        this.action = action;
    }
    
    public Action getAction(){
        return action;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    
    private void setPlayerNumber() {
        int num = 0;
        int numberOfAces = 0;
        for (Card card : playerCards) {
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
        playerNumber = num;

    }
    
    public void reset(){
        stand = false;
        canDouble = true;
        bet= 0;
        playerCards.clear();
        playerNumber = 0;
    }

    boolean isBust() {
        if(playerNumber > 21){
            return true;
        }else{
            return false;
        }
    }
    
    boolean isStand(){
        return stand;
    }
    
    public void setStand(){
        stand = true;
        canDouble = false;
    }
    
    public boolean canDouble(){
        return canDouble;
    }
    
    public boolean canSplit(){
        if(playerCards.size() == 2){
            if(playerCards.get(0).getRank().equals(playerCards.get(1).getRank())){
                return true;
            }
        }
        return false;
    }
    
    public int getBet(){
        return bet;
    }
    
    public void addChips(int chips){
        bet+= chips;
    }
    
    public void setCanDouble(boolean in){
        this.canDouble = in;
    }
    

}
