package com.example.chatandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.chatandroid.api.UserAPI;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private messagesDB db;
    private messagesDao dao;
   // private EditText editTextItem;
    private ContactUser contactUser;
    private messagesAdapter messsagesAdapter;
    private ArrayList<Message> arrayList;
    TextView userNameView;
    ListView messagesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messageslayout);

        db = Room.databaseBuilder(getApplicationContext(), messagesDB.class, "messagesDB").allowMainThreadQueries().build();
        dao = db.messagesDao();

        UserAPI userAPI = new UserAPI(dao);
        userAPI.getMessages("test");
        dao.update();

        userNameView = findViewById(R.id.user_name);

        Intent activityIntent = getIntent();

        String userName = activityIntent.getStringExtra("username");
        String userName2 = activityIntent.getStringExtra("username2");

        userNameView.setText(userName);


        Message message1 = new Message("Shahar", "Ido2", "HEY","test",true);
        Message message2 = new Message("Shahar", "Ido2", "HEY2","test",true);
        Message message3 = new Message("Shahar", "Ido2", "HEY3","test",true);
        Message message4 = new Message("Shahar", "Ido", "HEY3","test",true);

        ArrayList<Message> arrayListTemp = new ArrayList<>();
        arrayListTemp.add(message1);
        arrayListTemp.add(message2);
        arrayListTemp.add(message3);
        arrayListTemp.add(message4);


        for(int i = 0; i < 4; i++) {
            try {
                dao.insert(arrayListTemp.get(i));
            }
            catch (Exception e) {
                dao.update(arrayListTemp.get(i));
            }
        }

        messagesListView = findViewById(R.id.MessagesListView);

        arrayList = new ArrayList<>(dao.get(userName, userName2));

        messsagesAdapter = new messagesAdapter(getApplicationContext(), arrayList);

        messagesListView.setAdapter(messsagesAdapter);






//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB")
//                .allowMainThreadQueries()
//                .build();
//
//        contactUsersDao = db.contactUsersDao();
//
//        editTextItem = findViewById(R.id.editTextItem);
//
//
//        if (getIntent().getExtras() != null) {
//            int id = getIntent().getExtras().getInt("id");
//            contactUser = contactUsersDao.get(id);
//            editTextItem.setText(contactUser.getContent());
//        }
    }

}