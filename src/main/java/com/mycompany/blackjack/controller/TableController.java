/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Action;
import com.mycompany.blackjack.model.Setup;
import com.mycompany.blackjack.model.Hand;
import com.mycompany.blackjack.model.Table;
import com.mycompany.blackjack.model.Player;
import com.mycompany.blackjack.model.Seat;
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
public class TableController {

    private String tableName;

    @Inject
    private Setup game;

    private Table table;

    @Inject
    private Player player;

    private String userName;

    private String gameMessage;

    @PostConstruct
    public void init() {
        
    }

    public void onLoad() {
        table = game.getTable(tableName);
    }
    
    public void joinSeat(Seat seat){
        table.joinSeat(seat.getSeatNumber(), player);
    }

    public void leaveSeat(Seat seat) {
        if (seat.getPlayer().equals(player)) {
            table.leaveSeat(seat);
            
        }
    }
    
    public boolean thisPlayersGo(){
        if(table.getCurrentHand()==null){
            return false;
        }else if(table.getCurrentHand().getPlayer().equals(player)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean thisIsTheCurrentHand(Hand hand){
        if(table.getCurrentHand()==null)return false;
        return table.getCurrentHand().equals(hand);
    }
    
    public void doHit(){
        if(thisPlayersGo()){
            table.getCurrentHand().setAction(Action.HIT);
        }
    }
    
    public void doStand(){
        if(thisPlayersGo()){
            table.getCurrentHand().setAction(Action.STAND);
        }
    }
    
    public void doDouble(){
        if(thisPlayersGo()){
            table.getCurrentHand().setAction(Action.DOUBLE);
        }
    }
    
    public void doSplit(){
        if(thisPlayersGo()){
            table.getCurrentHand().setAction(Action.SPLIT);
        }
    }
    
    public boolean canDouble(){
        return table.getCurrentHand().canDouble();
    }
    
    public boolean canSplit(){
        return table.getCurrentHand().canSplit();
    }

    public void setPlayerName() {
        System.out.println("setting username" + userName);
        player.setUserName(userName);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }

    public void sendGameMessage() {
        table.addGameMessage(player.getUserName(), gameMessage);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    public boolean isThisPlayerInSeat(Seat seat){
        if(seat.getPlayer() == null){
            return false;
        }else if(seat.getPlayer().equals(player)){
            return true;
        }else{
            return true;
        }
    }
    
    public boolean canStart(){
        return table.canStart();
    }
    
    public void start(){
        
    }

}
