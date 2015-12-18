package com.example.dmitry.myapplication_fragment;

import java.io.Serializable;

/**
 * Created by Dmitry on 17.12.2015.
 */
public class Items implements Serializable {
    public String name_item;
    Boolean isstrikeout=false;

    Items(String name_item){
        this.name_item=name_item;
        this.isstrikeout=false;
    }

    public void setName_item(String name_item){
        this.name_item=name_item;
    }
    public String getName_item(){
        return name_item;
    }
    public void setIsstrikeout(boolean isstrikeout){
        this.isstrikeout=isstrikeout;
    }
    public boolean IsStrikeout(){
        return isstrikeout;
    }
}
