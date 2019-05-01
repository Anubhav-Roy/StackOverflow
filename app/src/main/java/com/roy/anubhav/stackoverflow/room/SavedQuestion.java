package com.roy.anubhav.stackoverflow.room;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//POJO for a question to be saved
@Entity
public class SavedQuestion {

    @PrimaryKey(autoGenerate=true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "link")
    public String link;


}
