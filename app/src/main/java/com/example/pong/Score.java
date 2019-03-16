package com.example.pong;
import java.util.Date;
public class Score implements Comparable<Score>{
    private String name;
    private int number;
    private String uid;
    private Date timeScored;
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
        this.timeScored = new Date();
    }
    public int compareTo(Score other)
    {
        if (other.number != this.number)
            return this.number-other.number;
        else
            return this.name.compareTo(other.getName());

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
    public Date getDate()
    {
        return timeScored;
    }
    public String toString()
    {
        return name + ": " + number;
    }



}
