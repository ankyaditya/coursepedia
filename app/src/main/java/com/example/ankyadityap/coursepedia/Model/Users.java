package com.example.ankyadityap.coursepedia.Model;

public class Users {
    private String name, nim, password, image, score;

    public Users() {
    }

    public Users(String name, String nim, String password, String image, String score) {
        this.name = name;
        this.nim = nim;
        this.password = password;
        this.image = image;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
