package com.example.contactsmanager;

//this class is a entity.
//an entity represents a table in SQLite database.
//each entity corresponds to 1 table and field parameters or variabbles in the entity class represents the INT columns in the table.

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//for naming table
@Entity(tableName = "contacts_table")
//if table name is not specified then the tablename is same as entity class name.
public class Contacts {

    //now name of id column is contact_id.
    @ColumnInfo(name = "contact_id")//this annotation provides control over column specific properties.
    @PrimaryKey(autoGenerate = true)//SQLite will automatically allocate unique ids.
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;


    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Ignore
    public Contacts(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
