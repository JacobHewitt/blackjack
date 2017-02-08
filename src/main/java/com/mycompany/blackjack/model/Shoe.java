/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jakeh
 */
public class Shoe {
    
    private List<Card> removedCards;
    private List<Card> cards;
    private int numberOfDecks;
    
    private int cardCount;
    
    private float trueCardCount;
    
    public Shoe(int numberOfDecks){
        this.numberOfDecks = numberOfDecks;
        removedCards = new ArrayList<Card>();
        cards = new ArrayList<Card>();
        setNumberOfDecks(numberOfDecks);
    }
    
    public void setNumberOfDecks(int num){
        for(int x = 0; x<num;x++){
            Deck toAdd = new Deck();
            cards.addAll(toAdd.getCards());
        }
        shuffle();
    }
    
    public Card drawCard(){
        Card toReturn = null;
        toReturn = cards.remove(0);
        addCardToCardCount(toReturn);
        setTrueCardCount();
        return toReturn;
    }
    
    private void addCardToCardCount(Card card){
        switch(card.getRank()){
            case CARD_2: cardCount++;
                break;
            case CARD_3: cardCount++;
                break;
            case CARD_4: cardCount++;
                break;
            case CARD_5: cardCount++;
                break;
            case CARD_6: cardCount++;
                break;
            case CARD_10: cardCount--;
                break;
            case JACK: cardCount--;
                break;
            case QUEEN: cardCount--;
                break;
            case KING: cardCount--;
                break;
            case ACE: cardCount--;
                break;
            
        }
    }
    
    private void setTrueCardCount(){
        trueCardCount = (float) cardCount / numberOfDecks;
    }
    
    public int cardsLeft(){
        return cards.size();
    }
    
    public void shuffle(){
        Collections.shuffle(cards);
    }
    
    public int getCardCount(){
        return cardCount;
    }
    
    public float getTrueCardCount(){
        return trueCardCount;
    }
    
}
