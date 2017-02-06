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
public class Card {
    
    private final Suit suit;
    private final Rank rank;
    
    private int value;
    
    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        value = getValueFirstTime();
    }
    
    @Override
    public String toString(){
        return suit+""+rank+".png";
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public int getValue(){
        return value;
    }
    
    private int getValueFirstTime(){
        int toReturn = 0;
        switch(rank){
            case CARD_2: toReturn = 2;
                break;
            case CARD_3: toReturn = 3;
                break;
            case CARD_4: toReturn = 4;
                break;
            case CARD_5: toReturn = 5;
                break;
            case CARD_6: toReturn = 6;
                break;
            case CARD_7: toReturn = 7;
                break;
            case CARD_8: toReturn = 8;
                break;
            case CARD_9: toReturn = 9;
                break;
            case CARD_10: toReturn = 10;
                break;
            case JACK: toReturn = 10;
                break;
            case QUEEN: toReturn = 10;
                break;
            case KING: toReturn = 10;
                break;
            case ACE: toReturn = 0;
                break;
        }
        return toReturn;
    }
    
    
    
}
