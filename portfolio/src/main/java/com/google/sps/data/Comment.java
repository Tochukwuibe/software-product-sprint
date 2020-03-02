package com.google.sps.data;

public class Comment {

    private String text;
    private final long id;
    private final long timestamp;


    public Comment(String text) {
        this(text, System.currentTimeMillis(), 1000l);
    }

      public Comment(String text, long timestamp, long id) {
        this.text = text;
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }
    public long getId() {
        return id;
    }
    public long getTimeStamp() {
        return timestamp;
    }

}