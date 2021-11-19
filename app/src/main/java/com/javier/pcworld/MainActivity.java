package com.javier.pcworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javier.pcworld.room_database.Register;
import com.javier.pcworld.room_database.RegisterLab;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText login,password;
    RegisterLab mRegisterLab;
    Register mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(EditText)findViewById(R.id.edt_login);
        password=(EditText)findViewById(R.id.edt_password);
        mRegisterLab = RegisterLab.get(this);
        List<Register> tasks = mRegisterLab.getAllTask();
        if(tasks.size() > 0) {
            mRegister = tasks.get(0);
        }

    }

    public void funLogin(View view)
    {
        /*consumir api rest para consumo del servicio, historia 12*/
        String loginTmp=login.getText().toString();
        String passwordTmp=password.getText().toString();
        if(loginTmp.equals(mRegister.login) && passwordTmp.equals(mRegister.password))
        {
            Variables.user_id=String.valueOf(mRegister.id);
            Variables.user_name=mRegister.name;
            Variables.user_money=mRegister.money;
            Variables.user_login=mRegister.login;
            Variables.user_password=mRegister.password;
            Intent intento=new Intent(MainActivity.this, ProductosActivity.class);
            startActivity(intento);
        }
        else
        {
            Variables.user_id=null;
            Variables.user_name=null;
            Variables.user_money=null;
            Variables.user_login=null;
            Variables.user_password=null;
            Toast.makeText(this,getString(R.string.error_login_text),Toast.LENGTH_LONG).show();
        }
    }

    public void funRegister(View view)
    {
        Intent intento=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intento);
    }
}