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
        return suit+" "+rank;
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
            case ONE: toReturn = 1;
                break;
            case TWO: toReturn = 2;
                break;
            case THREE: toReturn = 3;
                break;
            case FOUR: toReturn = 4;
                break;
            case FIVE: toReturn = 5;
                break;
            case SIX: toReturn = 6;
                break;
            case SEVEN: toReturn = 7;
                break;
            case EIGHT: toReturn = 8;
                break;
            case NINE: toReturn = 9;
                break;
            case TEN: toReturn = 10;
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
