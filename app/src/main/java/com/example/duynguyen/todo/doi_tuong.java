package com.example.duynguyen.todo;

public class doi_tuong {
    private String title;
    private boolean heart;

    public doi_tuong(){

    }

    public doi_tuong(String title, boolean heart) {
        this.title = title;
        this.heart = heart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getHeart() {
        return heart;
    }

    public void setHeart(boolean heart) {
        this.heart = heart;
    }
}
