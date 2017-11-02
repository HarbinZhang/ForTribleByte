package com.harbin.fortriplebyte.database;

/**
 * Created by Harbin on 11/2/17.
 */

public class Transaction {

    public String name;
    public double cost;
    public String date;
    public String type;

    public Transaction(){

    }

    public Transaction(String type, String name, double cost, String date){
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.date = date;
    }
}
