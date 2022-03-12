package com.revature.server;

import com.revature.domain.Notes;

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
import java.util.stream.Stream;

public class DatabaseController {

    /**
     *
     */
    public void BuildDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public List<Notes> GetAllData() {
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
        return notesList;
    }

    /**
     *
     */
    public void InsertData(Notes note) {
//        select SENDER_ID, count(SENDER_ID) from SENDERS group by SENDER_ID having count(SENDER_ID) > 1;
        int randId = GenerateId();
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public int GenerateId() {
        Random random = new Random();
        List<Integer> randomList = IntStream.range(1, 10).map(i -> random.nextInt(1000)).boxed().distinct().toList();
        Collections.shuffle(randomList);
        System.out.println(randomList.get(0));
        return randomList.get(0);
    }
}
