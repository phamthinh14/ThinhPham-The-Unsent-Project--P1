package com.revature.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            notesList.forEach(notes -> System.out.println(notes));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    void insertData() {
//        int randId = generateId();
//        ObjectMapper mapper = new ObjectMapper();
//        Artist newArtist = mapper.readValue(req.getInputStream(), Artist.class);
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    int generateId() {
        Random random = new Random();
        List<Integer> randomList = IntStream.range(1, 10).map(i -> random.nextInt(1000)).boxed().distinct().toList();
        Collections.shuffle(randomList);
        System.out.println(randomList.get(0));
        return randomList.get(0);
    }
}