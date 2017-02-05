/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jakeh
 */
public class Seat {
    
    private Player player;
    
    private List<Hand> hands;
    
    private int bet;
    
    private int seatNumber;
    
    public Seat(int seatNumber){
        this.seatNumber=seatNumber;
        
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hand) {
        this.hands = hand;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
    
    public boolean isEmpty(){
        return player==null;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public void resetHands(){
        hands = new LinkedList<Hand>();
    }
    
    public void addHand(Hand hand){
        this.hands.add(hand);
    }
    
    
}
