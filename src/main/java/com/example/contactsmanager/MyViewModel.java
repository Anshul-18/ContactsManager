package com.example.contactsmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
//import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {

    //if you need to use context inside your viewModel
    //you should use AndroidViewModel(AVM),
    //cauz it contains the app context.

    private Repository myRepository;
    //livedata
    public LiveData<List<Contacts>> allContacts;


    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    //Androidviewmodel is a subclass of viewmodel and similar to them, they are designed to
    // store and manage UI related data are responsible to prepare & provide data for UI
    // and automatically allow data to survive configuration change..

    public LiveData<List<Contacts>> getAllContacts() {
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact){
        myRepository.addContact(contact);
    }

    public void deleteContact(Contacts contact){
        myRepository.deleteContact(contact);
    }


}
