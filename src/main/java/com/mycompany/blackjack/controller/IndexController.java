/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Setup;
import com.mycompany.blackjack.model.Table;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author jakeh
 */
@ManagedBean
public class IndexController {
    
    @Inject
    private Setup game;
    
    public List<Table> getAllTables(){
        return game.getTables();
    }
    
}
