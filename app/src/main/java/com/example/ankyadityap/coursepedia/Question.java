package com.example.ankyadityap.coursepedia;

public class Question {
    public String[] slide_pertanyaan = {
            "1. Berapakah 2 x 4 ?",
            "2. Berapa 200 x 2 : 5 ?",
            "3. Berapa 30 x 3 ?",
            "4. Siapa Fajar?",
            "5. Aku adalah laba-laba"
    };

    public String[][] answer = {
            {"8", "4", "12", "2",},
            {"100", "80", "20", "60"},
            {"5", "4", "12", "90",},
            {"Aku", "Kamu", "Kita", "Mereka"},
            {"Super", "Bat", "Spider", "Wonder"}
    };

    public String[] slide_desc = {
            "8",
            "80",
            "90",
            "Aku",
            "Spider"
    };


    public String getQuestion(int nomor) {
        String tanya = slide_pertanyaan[nomor];
        return tanya;
    }

    public String getJawsb1(int nomor) {
        String jawab = answer[nomor][0];
        return jawab;
    }

    public String getJawsb2(int nomor) {
        String jawab = answer[nomor][1];
        return jawab;
    }

    public String getJawsb3(int nomor) {
        String jawab = answer[nomor][2];
        return jawab;
    }

    public String getJawsb4(int nomor) {
        String jawab = answer[nomor][3];
        return jawab;
    }

    public String getAnswer(int nomor) {
        String jawab = slide_desc[nomor];
        return jawab;
    }
}
