package com.example.contactsmanager;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao//DATA ACCESS OBJECT
//dao is an interface that provides methods for interacting with database with methods like inserting, deleting, updating etc.,
public interface ContactDAO {

    @Insert
    void insert(Contacts contact);

    @Delete
    void delete(Contacts contact);

    //you can specify custom queries in sql with Query annotation.
    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getALlContacts();
    //livedata for realtime updates and changes.

}
