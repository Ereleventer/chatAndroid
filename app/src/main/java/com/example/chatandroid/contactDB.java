package com.example.chatandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ContactUser.class}, version = 1)
public abstract class contactDB extends RoomDatabase {

    public abstract ContactUsersDao contactUsersDao();
}
