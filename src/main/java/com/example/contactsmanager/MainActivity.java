package com.example.contactsmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //datasource
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    //adapter
    private MyAdapter myAdapter;

    //binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);

        mainBinding.setClickHandler(handlers);

        //RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerview;//USED TO OBTAIN A REFERENCE TO A RECYCLERVIEW widget
        // from a layout that used DATABINDING.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);




        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        //this is used to get, create & retrieve an instance of a viewModel associated with a specific activity or fragment.

        //insert a new contact just for testing
        viewModel.addNewContact(new Contacts("Jack", "Jack@gmail.com"));

        //Loading data from ROOM DB
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();//it prevents duplication of items everytime we run app.

                for(Contacts c: contacts){
                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);
                }


                myAdapter.notifyDataSetChanged();

            }
        });
        //observe method is used to listen changes in the data held by the livedata object.

        //adapter
        myAdapter = new MyAdapter(contactsArrayList);

        //linking the recyclerView with the adapter.
        recyclerView.setAdapter(myAdapter);

        //swipe to delete

        //item touch helper provide support for every touch swipe gesture in android.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContact(c);

            }
        }).attachToRecyclerView(recyclerView);




        //THIS IS HOW MVVM ARCH. WORKS.


    }
}