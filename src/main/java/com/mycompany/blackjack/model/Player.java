/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean
public class Player {
    
    private String userName;
    private List<Hand> playerHands = new LinkedList<Hand>();
    
    public Player(){
        userName = "default";
        playerHands = new LinkedList<>();
    }

    public List<Hand> getPlayerHands() {
        return playerHands;
    }

    public void setPlayerHands(List<Hand> playerHands) {
        this.playerHands = playerHands;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
    
    
}
