/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.persistence;

import com.mycompany.blackjack.entitys.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jakeh
 */
@Stateless
public class PlayerFacade extends AbstractFacade<Player> {

    @PersistenceContext(unitName = "com.mycompany_blackjack_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerFacade() {
        super(Player.class);
    }
    
    public Player getPlayerByEmail(String email){
        List<Player> toReturn = em.createNamedQuery("findPlayerByEmail", Player.class).setParameter("email", email).getResultList();
        if(toReturn.size()>0){
            return toReturn.get(0);
        }else{
            return null;
        }
    }
    
}
