package com.example.alex.dictionary;

public class Type {
    int id;
    public String word;
    public String translate;

    Type(String word, String translate, int id) {
        this.id = id;
        this.word = word;
        this.translate = translate;
    }
}
