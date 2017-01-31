/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author jakeh
 */
@Stateful
public class Deck {
    
    private List<Card> cards;
    private List<Card> drawnCards;
    
    public Deck(){
        cards = new LinkedList<>();
        drawnCards = new LinkedList<>();
        for(Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                cards.add(new Card(suit, rank));
            }
        }
    }
    
    public void shuffle(){
        cards.addAll(drawnCards);
        drawnCards.clear();
        Collections.shuffle(cards);
    }
    
    public Card drawCard(){
        Card toReturn = cards.remove(0);
        drawnCards.add(toReturn);
        return toReturn;
    }
    
}
