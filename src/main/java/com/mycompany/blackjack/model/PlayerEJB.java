/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import com.mycompany.blackjack.entitys.Player;
import com.mycompany.blackjack.persistence.PlayerFacade;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PlayerEJB {
    
    @Inject
    private PlayerFacade playerFacade;
    
    public Player getPlayerByEmail(String email){
        return playerFacade.getPlayerByEmail(email);
    }
    
    public void createPlayer(Player player){
        playerFacade.create(player);
    }
    
}
