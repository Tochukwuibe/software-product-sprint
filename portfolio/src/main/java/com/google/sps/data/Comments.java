package com.google.sps.data;

import java.util.ArrayList; 
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

public class Comments {
    
    private Entity taskEntity;
    private DatastoreService datastore;
    private static Comments comments;
    private Query query;
    private Gson gson;

    private Comments() {
        this.taskEntity = new Entity("Comment"); 
        datastore = DatastoreServiceFactory.getDatastoreService();
        query = new Query("Comment");
        gson = new Gson();
    }


    public static Comments getInstance() {

        if(comments != null) {
            return comments;
        }

        comments = new Comments();

        return comments;
    }


    public Comment create(String text) {

        Comment comment = new Comment(text);

        taskEntity.setProperty("text",  comment.getText());
        taskEntity.setProperty("timestamp", comment.getTimeStamp());
        datastore.put(taskEntity);

        return comment;
    }

    public ArrayList<String> getComments() {
        query.addSort("timestamp", SortDirection.ASCENDING);

        PreparedQuery results = datastore.prepare(query);
        ArrayList<String> comments = new ArrayList<>();

        for (Entity entity : results.asIterable()) {

            long id = entity.getKey().getId();
            String text = (String) entity.getProperty("text");
            long timestamp = (long) entity.getProperty("timestamp");

             Comment comment = new Comment(text, timestamp, id);
             String json = gson.toJson(comment);

            comments.add(json);

        }

        return comments;
    }
}