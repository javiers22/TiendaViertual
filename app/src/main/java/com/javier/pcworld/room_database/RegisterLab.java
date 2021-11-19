package com.javier.pcworld.room_database;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class RegisterLab
{
    @SuppressLint("StaticFieldLeak")
    private static RegisterLab sRegisterLab;

    private RegisterDAO mRegisterDao;

    private RegisterLab(Context context) {
        Context appContext = context.getApplicationContext();
        RegisterDataBase database = Room.databaseBuilder(appContext, RegisterDataBase.class, "register")
                .allowMainThreadQueries().build();
        mRegisterDao = database.registerDao();
    }

    public static RegisterLab get(Context context) {
        if (sRegisterLab == null) {
            sRegisterLab = new RegisterLab(context);
        }
        return sRegisterLab;
    }

    public List<Register> getAllTask() {
        return mRegisterDao.getAllTask();
    }


    public long insertTask(Register task) {
        long result=mRegisterDao.insertTask(task);
        return result;
    }

    public void updateTask(Register task) {
        mRegisterDao.updateTask(task);
    }

    public void deleteTask(Register task) {
        mRegisterDao.deleteTask(task);
    }
}
