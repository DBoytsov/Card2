package com.example.dmitry.myapplication_fragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dmitry on 04.11.2015.
 */
public class ProductCardList extends ArrayList implements Serializable {
    ArrayList<Person> mPerson;

    public ProductCardList(){
        mPerson=new ArrayList<Person>();
    }

    public void addPerson(Person c) {
        mPerson.add(c);
    }
    public ArrayList<Person> getmPerson(){
        return mPerson;
    }
}
