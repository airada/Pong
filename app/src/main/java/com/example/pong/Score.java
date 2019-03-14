package com.example.pong;

public class Score {
    private String name;
    private int number;
    private String uid;

    public Score()
    {
        name ="NA";
        number=0;
    }

    public Score( String name, int number, String uid )
    {
        this.name = name;
        this.number = number;
        this.uid = uid;
    }

    public String getName()
    {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    public String getUid()
    {
        return uid;
    }
    public String toString()
    {
        return name + ": " + number;
    }
}
