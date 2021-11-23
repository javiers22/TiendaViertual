package com.javier.pcworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.javier.pcworld.room_database.Register;
import com.javier.pcworld.room_database.RegisterLab;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText login;
    EditText password;
    EditText money;
    EditText name;
    RegisterLab mRegisterLab;
    Register mRegister;
    Object resultString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login=(EditText)findViewById(R.id.edt_login);
        password=(EditText)findViewById(R.id.edt_password);
        money=(EditText)findViewById(R.id.edt_money);
        name=(EditText)findViewById(R.id.edt_name);
        mRegisterLab = RegisterLab.get(this);
        List<Register> tasks = mRegisterLab.getAllTask();
        if(tasks.size() > 0) {
            mRegister = tasks.get(0);
            login.setText(mRegister.login);
            password.setText(mRegister.password);
            money.setText(mRegister.money);
            name.setText(mRegister.name);
        }
    }

    public void funRegister(View view)
    {
        /*consumir api rest para registro del usuario*/
        SegundoPlano tarea=new SegundoPlano();
        tarea.execute();
        /*fin*/
        String loginTmp=login.getText().toString();
        String passwordTmp=password.getText().toString();
        String moneyTmp=money.getText().toString();
        String nameTmp=name.getText().toString();
        if(!login.equals(""))
        {
            if (mRegister == null)
            {
                mRegister = new Register();
                mRegister.login = loginTmp;
                mRegister.password = passwordTmp;
                mRegister.money = moneyTmp;
                mRegister.name = nameTmp;
                long result = mRegisterLab.insertTask(mRegister);
                Toast.makeText(this, "Insert exitoso " + String.valueOf(result), Toast.LENGTH_SHORT).show();
            }
            else
            {
                mRegister.login = loginTmp;
                mRegister.password = passwordTmp;
                mRegister.money = moneyTmp;
                mRegister.name = nameTmp;
                mRegisterLab.updateTask(mRegister);
                Toast.makeText(this, "Update exitoso", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Hay que editar la tarea primero",Toast.LENGTH_SHORT).show();
        }

            /*fin consumir api rest para registro de usuario*/
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.correct_register)).setMessage(getString(R.string.correct_register));
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //al jhacer click en ok se devuelve a la aterior actividad
                Variables.user_id=null;
                Variables.user_name=null;
                Variables.user_money=null;
                Variables.user_login=null;
                Variables.user_password=null;
                Intent intento=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intento);
            }
        });
        dialog.create().show();
    }

    private class SegundoPlano extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {

        }
        @Override
        protected Void doInBackground(Void... voids) {
            consumir();
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            String mensaje="hola";
        }
    }

    public void consumir()
    {
        String SOAP_ACTION="https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php/regiter_activity";
        String METHOD_NAME="regiter_activity";
        String NAMESPACE="https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php";
        String URL="https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php";//webservice sin wsdl
        try
            {
                SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME);
                request.addProperty("login",login.getText().toString());
                request.addProperty("password",password.getText().toString());
                request.addProperty("money",money.getText().toString());
                request.addProperty("name",name.getText().toString());
                SoapSerializationEnvelope soapEnvelope= new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet=true;
                soapEnvelope.setOutputSoapObject(request);
                HttpTransportSE transport=new HttpTransportSE(URL);
                transport.call(SOAP_ACTION,soapEnvelope);
                resultString=(Object)soapEnvelope.getResponse();
            }
        catch(Exception ex)
            {
                System.out.println(ex.getMessage()+" error consumiendo");
            }
    }
}