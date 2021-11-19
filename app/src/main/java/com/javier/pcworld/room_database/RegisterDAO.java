package com.javier.pcworld.room_database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RegisterDAO
{
    @Query("SELECT * FROM usuarios")
    List<Register> getAllTask();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertTask(Register usuario);

    @Delete
    void deleteTask(Register usuario);

    @Update
    void updateTask(Register usuario);
}
