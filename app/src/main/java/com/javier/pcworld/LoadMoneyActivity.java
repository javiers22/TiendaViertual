package com.javier.pcworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.javier.pcworld.room_database.Register;
import com.javier.pcworld.room_database.RegisterLab;

import java.util.List;

public class LoadMoneyActivity extends AppCompatActivity {

    EditText userMoney;
    RegisterLab mRegisterLab;
    Register mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_money);
        userMoney=(EditText)findViewById(R.id.edt_money);
        mRegisterLab = RegisterLab.get(this);
        List<Register> tasks = mRegisterLab.getAllTask();
        if(tasks.size() > 0) {
            mRegister = tasks.get(0);
            userMoney.setText(mRegister.money);
        }
    }

    public void funLoadMoney(View view)
    {
        String userMoneyTmp=userMoney.getText().toString();
        /*Consumir api rest para agregar dinero a la tarjeta del usuario*/
        int dinero=Integer.parseInt(Variables.user_money)+Integer.parseInt(userMoneyTmp);
        mRegister = new Register();
        mRegister.login = Variables.user_login;
        mRegister.password = Variables.user_password;
        mRegister.money = Variables.user_money;
        mRegister.name = Variables.user_name;
        mRegisterLab.updateTask(mRegister);
        /*fin condumir api rest*/
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.correct_register)).setMessage(getString(R.string.correct_register));
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //al jhacer click en ok se devuelve a la aterior actividad
                int dinero=Integer.parseInt(Variables.user_money)+Integer.parseInt(userMoneyTmp);
                Variables.user_money=String.valueOf(dinero);
                Intent intento=new Intent(LoadMoneyActivity.this,ProductosActivity.class);
                startActivity(intento);
            }
        });
        dialog.create().show();
    }
}