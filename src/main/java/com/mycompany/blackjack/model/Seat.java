/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

/**
 *
 * @author jakeh
 */
public class Seat {
    
    private Player player;
    
    private Hand hand;
    
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

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
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
    
    
    
    
}
