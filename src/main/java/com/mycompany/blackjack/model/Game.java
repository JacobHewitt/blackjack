/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.blackjack.model;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author jakeh
 */
@Startup
@Singleton
public class Game {
    
    private List<Table> tables;
    private List<Player> players;
    
    @PostConstruct
    public void init(){
        tables = new LinkedList<Table>();
        players = new LinkedList<Player>();
        Table firstTable = new Table();
        firstTable.setTableName("firstTable");
        tables.add(firstTable);
    }
    
    public List<Table> getTables(){
        return tables;
    }
    
    public Table getTable(String tableName){
        for(Table table:tables){
            if(table.getTableName().equals(tableName)){
                return table;
            }
        }
        return null;
    }
    
    
    
}
