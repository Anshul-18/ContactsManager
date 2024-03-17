package com.example.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    //repository is a class mainly used to manage multiple sources of data.
    //THIS CLASS ISOLATES DATA SOURCES FROM THE REST OF THE APP & PROVIDE A CLEAN API FOR DATA ACCESS TO THE APP.

    //Available data sources:
    //-ROOM Database

    private final ContactDAO contactDAO;

    //executing handler, executor & runnable.
    ExecutorService executor;
    Handler handler;


    public Repository(Application application) {

        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        executor = Executors.newSingleThreadExecutor();//to avoid concurrency issues.

        //THIS ENSURES ANY UI RELATED UPDATE IS DONE ON MAIN THREAD.
        handler = new Handler(Looper.getMainLooper());

    }

    //EVERY METHOD YOU CREATED IN DAO YOU SHOULD MENTION HERE
    //METHODS IN DAO IS BEING EXECUTED FROM REPOSITORY.
    public void addContact(Contacts contact){

        //Runnable: executing tasks on separate thread.
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });

    }

    public  void deleteContact(Contacts contact){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });

    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getALlContacts();
    }

}
