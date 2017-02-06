/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean
public class Player {
    
    private String userName;
    
    private int chips;
    
    public Player(){
        userName = "guest";
        chips = 1000;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }
    
    public void addChips(int chips){
        this.chips+=chips;
    }
    
    public int takeChips(int amount){
        chips -= amount;
        return amount;
    }
    
    
    
}
