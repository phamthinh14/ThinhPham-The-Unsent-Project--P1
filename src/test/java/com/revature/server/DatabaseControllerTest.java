package com.revature.server;

import com.revature.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseControllerTest {

    @BeforeEach
    void setUp() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllData() {
        //        select MESSAGES.RECEIVER_ID, SENDERS.SENDER_NAME, MESSAGES.RECEIVER_NAME, MESSAGES.MESSAGES from MESSAGES  inner join SENDERS on SENDERS.SENDER_ID = MESSAGES.RECEIVER_ID;
        List<Notes> notesList = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            ResultSet rs = conn.prepareStatement("select MESSAGES.RECEIVER_ID, SENDERS.SENDER_NAME, " +
                    "MESSAGES.RECEIVER_NAME, MESSAGES.MESSAGES from MESSAGES  inner join" +
                    " SENDERS on SENDERS.SENDER_ID = MESSAGES.RECEIVER_ID;").executeQuery();
            while (rs.next()) {
                notesList.add(
                        new Notes(rs.getInt("receiver_id"),
                                rs.getString("sender_name"),
                                rs.getString("receiver_name"),
                                rs.getString("messages")));
            }
            notesList.forEach(notes -> System.out.println(notes));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}