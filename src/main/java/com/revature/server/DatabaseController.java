package com.revature.server;

import com.revature.domain.Notes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public void BuildDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notes> GetAllData() {
//        select MESSAGES.RECEIVER_ID, SENDERS.SENDER_NAME, MESSAGES.RECEIVER_NAME, MESSAGES.MESSAGES from MESSAGES  inner join SENDERS on SENDERS.SENDER_ID = MESSAGES.RECEIVER_ID;
        List<Notes> notesList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            ResultSet rs = conn.prepareStatement("select * from messages").executeQuery();
            while (rs.next()) {
//                notesList.add(new Notes(rs.getInt("receiver_id"), rs.getString("receiver_name"), rs.getString("messages")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notesList;
    }
}
