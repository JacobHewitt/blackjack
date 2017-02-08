/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import com.mycompany.blackjack.model.Setup;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jakeh
 */
@Named
public class IndexController {

    @Inject
    private Setup game;

    @PostConstruct
    public void init() {
        
    }
//
//    public List<Table> getAllTables() {
//        return game.getTables();
//    }

}
