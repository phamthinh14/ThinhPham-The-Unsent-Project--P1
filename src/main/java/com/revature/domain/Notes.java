package com.revature.domain;

public class Notes {
    private int id;
    private String senderName;
    private String receiverName;
    private String messages;

    public Notes() {
    }

    public Notes(int id, String senderName, String receiverName, String messages) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", messages='" + messages + '\'' +
                '}';
    }
}
