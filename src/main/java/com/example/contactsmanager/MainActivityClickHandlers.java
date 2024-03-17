package com.example.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;

//all the clicks on mainActivity will be captured & handled here.
public class MainActivityClickHandlers {

    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view){

        Intent i = new Intent(view.getContext(), AddNewContactActivity.class);
        context.startActivity(i);
    }

}
