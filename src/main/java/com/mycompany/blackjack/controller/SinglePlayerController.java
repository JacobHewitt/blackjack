/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Card;
import com.mycompany.blackjack.model.Hand;
import com.mycompany.blackjack.model.SinglePlayerTable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jakeh
 */
@ManagedBean
@ViewScoped
public class SinglePlayerController {
    
    @Inject
    private SinglePlayerTable table;
    
    @PostConstruct
    public void init(){
        
    }
    
    public void start(){
        table.startGame();
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
    
}
