package com.example.contactsmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanager.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    //constructor
    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    //implemented methods.
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creating new view holders for items in recyclerview.
        //here you need to inflate layout for items & create new instance of your custom view holder.
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false
        );
        //NOTE : layoutinflator is used to inflate XML Layouts into view objects.

        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        //calledby recyclerView when it needs to display or update an item at a specific
        // position in the list or grid.

        Contacts currContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currContact);
    }

    @Override
    public int getItemCount() {
        //determines the total no. of items in the dataset that will be displayed in the recyclerView
        if(contacts != null){
            return  contacts.size();
        }else{
            return 0;
        }

    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        //It informs the associated recyclerview that the underlined data set has changed & the recyclerview
        // should refresh its views to reflect these changes.
        notifyDataSetChanged();
    }

    //dataBinding with recycler view.
    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;//passing refernce for contact_list_item.xml;

        public ContactViewHolder(@NonNull View itemView, ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());//get root method is used to obtain the root view of the layout associated with dataBinding.
            this.contactListItemBinding = contactListItemBinding;
        }

        public ContactViewHolder(ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
        }
    }

}
