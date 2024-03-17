package com.example.contactsmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//THIS CLASS WILL ACT AS DATABASE INSTANCE.
//NOTE:the database class is an "ABSTRACT CLASS" that servers as a database holder.
//it includes methods to access DAO's and create database instance.
@Database(entities = {Contacts.class}, version = 1)//NOW LATERON WHEN YOU UPDATE YOUR DATABASE YOU NEED TO INCREASE THIS VERSION NUMBER,
public abstract class ContactDatabase extends RoomDatabase {

    //to link database with DAO
    public abstract ContactDAO getContactDAO();

    //SINGLETON PATTERN: it ensures only one instance of the class exists throughout the lifecycle.
    private static ContactDatabase dbInstance;

    //synchronized :  synchronize methods or code blocks that access shared resources to prevent race conditions and ensure thread safety
    public static synchronized ContactDatabase getInstance(Context context){

        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();//build method to build the instance.

            //fallback method is used if new version(new rows or columns) of db is detected as per entity structure so ROOM will drop
            //and new db will be created with all data in it.

        }

        return dbInstance;


    }

}
