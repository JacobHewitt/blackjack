/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Card;
import com.mycompany.blackjack.model.Hand;
import com.mycompany.blackjack.model.SinglePlayerTable;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jakeh
 */
@Named
@ViewScoped
public class SinglePlayerController implements Serializable{
    
    @Inject
    SessionBean playerSession;
    
    @Inject
    private SinglePlayerTable table;
    
    private int betAmount;
    
    private String message;

    
    @PostConstruct
    public void init(){
        
    }
    
    public void start(){
        table.startGame(betAmount);
    }

    public SinglePlayerTable getTable() {
        return table;
    }

    public void setTable(SinglePlayerTable table) {
        this.table = table;
    }
    
    public List<Hand> getPlayerHands(){
        return table.getPlayer().getHands();
    }
    
    public List<Card> getDealerCards(){
        return table.getDealerCards();
    }
    
    public int getDealerCardsValue(){
        return table.getDealerNumber();
    }
    
    public boolean thisHandsTurn(Hand hand){
        if(table.getCurrentHand()==null)return false;
        return table.getCurrentHand().equals(hand);
    }
    
    public boolean isCurrentlyPlaying(){
        return table.isCurrentlyPlaying();
    }
    
    public void doHit(){
        table.hit();
    }
    
    public boolean canHit(){
        return table.canHit();
    }
    
    public void doStand(){
        table.stand();
    }
    
    public boolean canStand(){
        return table.canStand();
    }
    
    public boolean canDouble(){
        return table.canDouble();
    }
    
    public boolean canSplit(){
        return table.canSplit();
    }
    
    public void doDouble(){
        table.doDouble();
    }
    
    public void doSplit(){
        table.doSplit();
    }
    
    public boolean playerHasCards(){
        if(table.getPlayer().getHands()!=null)return true;
        return false;
    }
    
    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void sendMessage(){
        table.addGameMessage(playerSession.getPlayer().getFirstName(), message);
    }
    
    public boolean thisIsTheCurrentHand(Hand hand){
        if(table.getCurrentHand()!=null){
            return table.getCurrentHand().equals(hand);
        }
        return false;
    }
    
}
