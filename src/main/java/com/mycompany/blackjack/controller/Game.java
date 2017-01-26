/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Card;
import com.mycompany.blackjack.model.Deck;
import java.util.LinkedList;
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
public class Game {
    
    @Inject
    private Deck deck;
    
    private List<Card> playerCards = new LinkedList();
    private List<Card> dealerCards = new LinkedList();
    
    private int playerChips;
    
    private int bet;
    
    private int playerNumber;
    private int dealerNumber;
    
    @PostConstruct
    public void init(){
        playerChips = 1000;
    }
    
    public void start(){
        deck.shuffle();
        playerCards.add(deck.drawCard());
        dealerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public int getPlayerChips() {
        return playerChips;
    }

    public void setPlayerChips(int playerChips) {
        this.playerChips = playerChips;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }
    
    
    
    
    
    
}
