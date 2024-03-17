package com.example.contactsmanager;

//this class will handle the click events on 'submit ot room db button;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

public class AddNewContactClickHandler {

    Contacts contact;
    Context context;
    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contact, Context context, MyViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view) {
        if(contact.getName() == null || contact.getEmail() == null) {
            Toast.makeText(context, "enter something", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("Name", contact.getName());
//            i.putExtra("Email", contact.getEmail());
            //a better approach to add data to room db

            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );

            myViewModel.addNewContact(c);


            context.startActivity(i);

        }
    }


}
