package com.example.ankyadityap.coursepedia.Model;

public class Question {
    private String qid, date, time, description,image,matkul,qdosen, mhs, answer;

    public Question() {
    }

    public Question(String qid, String date, String time, String description, String image, String matkul, String qdosen, String mhs, String answer) {
        this.qid = qid;
        this.date = date;
        this.time = time;
        this.description = description;
        this.image = image;
        this.matkul = matkul;
        this.qdosen = qdosen;
        this.mhs = mhs;
        this.answer = answer;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getQdosen() {
        return qdosen;
    }

    public void setQdosen(String qdosen) {
        this.qdosen = qdosen;
    }

    public String getMhs() {
        return mhs;
    }

    public void setMhs(String mhs) {
        this.mhs = mhs;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
