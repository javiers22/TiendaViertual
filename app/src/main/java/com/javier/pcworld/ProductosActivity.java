package com.javier.pcworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProductosActivity extends AppCompatActivity {

    TextView username;
    TextView userMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        username=(TextView)findViewById(R.id.my_textview_username);
        username.setText(Variables.user_name);
        userMoney=(TextView)findViewById(R.id.my_textview_money);
        userMoney.setText("$"+Variables.user_money);
        Toast.makeText(this,getString(R.string.correct_login_text),Toast.LENGTH_LONG).show();
        setSupportActionBar(findViewById(R.id.my_toolbar));

        if(savedInstanceState==null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,ProductosFragment.class,null,"todo").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_productos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_logout:    AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                                        dialog.setTitle(getString(R.string.logout_title_text)).setMessage(getString(R.string.logout_title_text));
                                        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //al jhacer click en ok se devuelve a la aterior actividad
                                                Variables.user_id=null;
                                                Variables.user_name=null;
                                                Variables.user_money=null;
                                                Variables.user_login=null;
                                                Variables.user_password=null;
                                                Intent intento=new Intent(ProductosActivity.this,MainActivity.class);
                                                startActivity(intento);
                                            }
                                        });
                                        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // User cancelled the dialog
                                                //al hacer click en cancel se ceirra la alerta
                                            }
                                        });
                                        dialog.create().show();
                                        return true;
            case R.id.action_load_money:    Intent intento=new Intent(ProductosActivity.this,LoadMoneyActivity.class);
                                            startActivity(intento);
                                            return true;
            case R.id.action_buy_car:   /*getSupportFragmentManager()
                                        .beginTransaction()
                                        .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view))
                                        .commit();*/
                                        getSupportFragmentManager()
                                        .beginTransaction()
                                        .setReorderingAllowed(true)
                                        .replace(R.id.fragment_container_view,CarroComprasFragment.class,null,"car_buy")
                                        .addToBackStack("")
                                        .commit();
                                        return true;
            default:    super.onOptionsItemSelected(item);
                        return true;
        }
    }
}