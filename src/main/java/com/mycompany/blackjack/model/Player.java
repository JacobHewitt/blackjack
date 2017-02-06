/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

public class Player {
    
    private String firstName;
    
    private String email;
    
    private int chips;
    
    public Player(){
        firstName = "guest";
        chips = 1000;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    
    public boolean isLoggedIn(){
        if(email == null){
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
