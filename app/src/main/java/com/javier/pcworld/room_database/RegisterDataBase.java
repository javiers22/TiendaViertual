package com.javier.pcworld.room_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Register.class}, version = 1)
public abstract class RegisterDataBase extends RoomDatabase
{
    public abstract RegisterDAO registerDao();
}
