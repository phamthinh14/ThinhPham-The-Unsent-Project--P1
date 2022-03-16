package com.revature.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.domain.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // It will add incremental automatically
        Assertions.assertEquals("Nathan", notesList.get(0).getSenderName());
        Assertions.assertEquals("I have been dealing with some wack", notesList.get(2).getMessages());
        Assertions.assertEquals("Thark Low", notesList.get(notesList.size() - 1).getReceiverName());
        Assertions.assertEquals("I got option", notesList.get(notesList.size() - 1).getMessages());
    }

    @Test
    void searchByName() {
        List<Notes> notesList = new ArrayList<>();
        String name = "Mr.Show";
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            ResultSet rs = conn.prepareStatement("select MESSAGES.RECEIVER_ID, SENDERS.SENDER_NAME, " +
                            "MESSAGES.RECEIVER_NAME, MESSAGES.MESSAGES from MESSAGES  inner join SENDERS on" +
                            " SENDERS.SENDER_ID = MESSAGES.RECEIVER_ID where MESSAGES.RECEIVER_NAME = " + "'" + name + "';")
                    .executeQuery();
            while (rs.next()) {
                notesList.add(
                        new Notes(rs.getInt("receiver_id"),
                                rs.getString("sender_name"),
                                rs.getString("receiver_name"),
                                rs.getString("messages")));
            }
            notesList.forEach(notes -> System.out.println(notes));
            Assertions.assertEquals("Nathan", notesList.get(0).getSenderName());
            Assertions.assertEquals("Aye stop showing off", notesList.get(1).getMessages());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertData() {
        int id1 = generateId();
        Notes note1 = new Notes(id1, "Simon", "Nami", "I really like KFC and KFT");
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement statementSender = conn.prepareStatement("insert into senders values (?, ?)");
            statementSender.setInt(1, note1.getId());
            statementSender.setString(2, note1.getSenderName());
            statementSender.executeUpdate();
            PreparedStatement statementMessages = conn.prepareStatement("insert into messages values (?, ?, ?)");
            statementMessages.setInt(1, note1.getId());
            statementMessages.setString(2, note1.getReceiverName());
            statementMessages.setString(3, note1.getMessages());
            statementMessages.executeUpdate();
            ResultSet rs = conn.prepareStatement("select * from MESSAGES" +
                    " where MESSAGES.RECEIVER_ID=" + id1 + ";").executeQuery();
            String receiverName = "";
            String mess = "";
            while (rs.next()) {
                receiverName += rs.getString(2);
                mess += rs.getString(3);
            }
            Assertions.assertEquals(receiverName, note1.getReceiverName());
            Assertions.assertEquals(mess, note1.getMessages());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int generateId() {
        Random random = new Random();
        List<Integer> randomList = IntStream.range(1, 10).map(i -> random.nextInt(1000)).boxed().distinct().toList();
//        System.out.println(randomList.get(0));
        return randomList.get(0);
    }
}

