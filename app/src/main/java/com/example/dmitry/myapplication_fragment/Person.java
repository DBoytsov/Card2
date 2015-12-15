package com.example.dmitry.myapplication_fragment;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dmitry on 01.11.2015.
 */
public class Person implements Serializable {
        private UUID mId;
        String name;
        String age;
        String photoId;

        Person(){
            this.mId=UUID.randomUUID();
        }

        Person(String name, String age, String photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.mId=UUID.randomUUID();
        }

        Person(String name, String age, String photoId, UUID mId) {
            this.name = name;
            this.age = age;
            this.photoId = photoId;
            this.mId=mId;
        }


     public String getName_person() {
                return name;
        }

        public void setName_person(String name){
                this.name=name;
        }
        public void setAge_person(String age){
                this.age=age;
        }
        public void setPhoto_person(String photo){
                this.photoId= photo;
        }
     public UUID getId() {
                return mId;
        }

    public void setId(String mId) {
        this.mId= UUID.fromString(mId);
    }

     public String getAge_person() {
             return age;
     }
     public String getPhoto_person() {
                return photoId;
        }
}