package com.revature.server;

import com.revature.domain.Notes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DatabaseController {
    Logger logger = LoggerFactory.getLogger(DatabaseController.class);

    /**
     * Build the database from the script schema.sql
     */
    public void BuildDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
//            logger.info(String.valueOf(conn));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select all the data with inner join of two tables
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
        logger.info(String.valueOf(notesList));
        return notesList;
    }

    /**
     * Insert Data to the database
     */
    public boolean InsertData(Notes note) {
//        select SENDER_ID, count(SENDER_ID) from SENDERS group by SENDER_ID having count(SENDER_ID) > 1;
        boolean isInserted = true;
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            PreparedStatement statementSender = conn.prepareStatement("insert into senders values (?, ?)");
            statementSender.setInt(1, note.getId());
            statementSender.setString(2, note.getSenderName());
            statementSender.executeUpdate();
            PreparedStatement statementMessages = conn.prepareStatement("insert into messages values (?, ?, ?)");
            statementMessages.setInt(1, note.getId());
            statementMessages.setString(2, note.getReceiverName());
            statementMessages.setString(3, note.getMessages());
            statementMessages.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            isInserted = false;
        }
        logger.info(String.valueOf(isInserted));
        return isInserted;
    }

    /**
     * Search and return a list of Notes
     * @param name
     * @return list of Notes
     */
    public List<Notes> SearchByName(String name) {
        List<Notes> notesList = new ArrayList<>();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(String.valueOf(notesList));
        return notesList;
    }
}
