package com.example.ankyadityap.coursepedia.Model;

public class Dosen {
    private String nim, name, password, image;

    public Dosen() {
    }

    public Dosen(String nim, String name, String password, String image) {
        this.nim = nim;
        this.name = name;
        this.password = password;
        this.image = image;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
