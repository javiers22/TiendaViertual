package com.javier.pcworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javier.pcworld.room_database.Register;
import com.javier.pcworld.room_database.RegisterLab;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText login,password;
    RegisterLab mRegisterLab;
    Register mRegister;
    Object resultString;
    String string;
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
        SegundoPlano tarea=new SegundoPlano();
        tarea.execute();
        try
        {
                JSONArray jsonArray = new JSONArray(string);

                try
                {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.has("login"))
                        {
                            String loginTmp=login.getText().toString();
                            String passwordTmp=password.getText().toString();
                            if(loginTmp.equals(jsonObject.getString("login")) && passwordTmp.equals(jsonObject.get("password")))
                            {
                                Variables.user_id=String.valueOf(jsonObject.getString("id"));
                                Variables.user_name=jsonObject.getString("name");
                                Variables.user_money=jsonObject.getString("money");
                                Variables.user_login=jsonObject.getString("login");
                                Variables.user_password=jsonObject.getString("password");
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
                            //cantidadProductos.add(jsonObject.getString("cantidad"));
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
                catch (Exception e)
                {

                }

        }
        catch (Exception e)
        {

        }

        /*String loginTmp=login.getText().toString();
        String passwordTmp=password.getText().toString();*/
        /*if(loginTmp.equals(mRegister.login) && passwordTmp.equals(mRegister.password))
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
        }*/
    }

    public void funRegister(View view)
    {
        Intent intento=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intento);
    }private class SegundoPlano extends AsyncTask<Void, Void, Void>
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

    public void consumir() {
        String SOAP_ACTION = "https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php/login";
        String METHOD_NAME = "login";
        String NAMESPACE = "https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php";
        String URL = "https://serviciostic.usantotomas.edu.co/servers/srv_tiendavirtual.php";//webservice sin wsdl
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("login", login.getText().toString());
            request.addProperty("password", password.getText().toString());
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);
            resultString = (Object) soapEnvelope.getResponse();
            string=resultString.toString();
        } catch (Exception ex) {
            System.out.println(ex.getMessage()+" error consumiendo");
        }
    }


}