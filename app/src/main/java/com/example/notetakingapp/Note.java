package com.example.notetakingapp;

public class Note {
    /*
    This class will connect the AddNoteActivity to the database.
    This class will allow new notes to be added to the database and also retrieval of the notes saved.
    The class has a structure to pass the data to the database - it has variables that will hold the data that will be eventually passed into SQL database
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //    Variables required to pass data to/ from the database table
    private long id;
    private String title;
    private String content;
    private String date;
    private String time;

    public Note(long id, String title, String content, String date, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }


    public Note(String title, String content, String date, String time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public Note(){
//        This simply creates an instance of the Note Class with no arguments
    }

}
