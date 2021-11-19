package com.javier.pcworld.room_database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class Register
{
    @PrimaryKey
    public int id;
    public String login;
    public String password;
    public String money;
    public String name;
}
