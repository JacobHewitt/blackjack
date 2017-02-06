/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.FBConnection;
import com.mycompany.blackjack.model.Player;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author jakeh
 */
@Named
@SessionScoped
public class SessionBean implements Serializable{

    private Player player;

    private String code;

    private FacebookClient facebookClient;

    public SessionBean(){
        player = new Player();
    }

    public void login() {
        if (code != null) {
            System.out.println("FACEBOOK LOGIN--------------------");

            FBConnection fbConnection = new FBConnection();
            facebookClient = new DefaultFacebookClient(fbConnection.getAccessToken(code), Version.VERSION_2_6);
            User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "first_name,last_name,email,id"));
            player.setEmail(me.getEmail());
            player.setFirstName(me.getFirstName());
            System.out.println(player.getEmail() + " " + player.getFirstName());

        }
    }

    public void logout() {
        player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLoggedIn() {
        if (this.player.getEmail() != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
