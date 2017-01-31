/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Game;
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
    private Game game;

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
        if(seat.getPlayer() == null){
            seat.setPlayer(player);
        }
    }

    public void leaveSeat(Seat seat) {
        if (seat.getPlayer().equals(player)) {
            table.leaveSeat(seat);
            table.addGameMessage(player.getUserName() + " has left a seat.");
        }
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

    public boolean playerIsInSeat(Seat seat) {
        if (player.equals(seat.getPlayer())) {
            return true;
        } else {
            return false;
        }
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }

    public void sendGameMessage() {
        System.out.println("SENDING GAME MESSAGE" + gameMessage);
        table.addGameMessage(gameMessage);
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
    

}
