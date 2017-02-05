/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.sql.Time;

/**
 *
 * @author jakeh
 */
public class GameMessage {
    
    private String author;
    private Time time;
    private String message;
    
    public GameMessage(String author, String message){
        this.author = author;
        this.message = message;
        this.time = new Time(System.currentTimeMillis());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
