/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.controller;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author jakeh
 */
@Named
@Stateless
public class WorkaroundInvalidXML {

    public String getRowWithClass(){
        return "<tr class='currentHand'>";
    }
    
    public String getRow() {
        return "<tr>";
    }

}
