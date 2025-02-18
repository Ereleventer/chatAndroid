package com.example.chatandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface messagesDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM Message WHERE firstUser =:firstUser AND secondUser =:secondUser")
    List<Message> get(String firstUser, String secondUser);

    @Insert
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);

}