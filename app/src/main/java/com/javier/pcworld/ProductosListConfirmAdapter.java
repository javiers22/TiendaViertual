package com.javier.pcworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.EventListener;

public class ProductosListConfirmAdapter extends RecyclerView.Adapter<ProductosListConfirmAdapter.ViewHolder>
{
    AppCompatActivity context;
    Bundle info;
    ArrayList<String> nomProductos;
    ArrayList<String> preciosProductos;
    ArrayList<String> cantidadProductos;
    ArrayList<String> idProductos;
    Button cofirm;

    public ProductosListConfirmAdapter(AppCompatActivity context, Bundle info)
    {
        this.context=context;
        this.info=info;
        this.nomProductos=(ArrayList<String>) this.info.getStringArrayList("nombres");
        this.preciosProductos=(ArrayList<String>) this.info.getStringArrayList("precios");
        this.cantidadProductos=(ArrayList<String>) this.info.getStringArrayList("cantidad");
        this.idProductos=(ArrayList<String>) this.info.getStringArrayList("id");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_list_items_confirm, parent, false);
        return new ProductosListConfirmAdapter.ViewHolder(myViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(nomProductos.get(position));
        holder.getTextView1().setText(preciosProductos.get(position));
        holder.getTextView2().setText(cantidadProductos.get(position));
        Button confirm=(Button)context.findViewById(R.id.button_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                int suma=0;
                for(int i=0;i<Variables.id_products.size();i++)
                {
                    int precio=Integer.parseInt(Variables.id_products.get(i).get(3).split(": ")[1]);
                    suma+=precio;
                }
                if(Integer.parseInt(Variables.user_money)>=suma)
                {
                    /*Consumir api rest historia 10*/
                    Toast.makeText(holder.itemView.getContext(), "Compra exitosa", Toast.LENGTH_LONG).show();
                    int dinero=Integer.parseInt(Variables.user_money)-suma;
                    Variables.user_money=String.valueOf(dinero);
                    Variables.name_product = null;
                    Variables.id_product = null;
                    Variables.cant_product = null;
                    Variables.price_product = null;
                    Variables.id_products.clear();
                }
                else
                {
                    Toast.makeText(holder.itemView.getContext(), "Sin fondos", Toast.LENGTH_LONG).show();
                }
                Intent intento = new Intent(context, ProductosActivity.class);
                context.startActivity(intento);
            }
        });

        /*holder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),holder.getTextView().getText(),Toast.LENGTH_LONG).show();
                Bundle datos=new Bundle();
                datos.putString("nombres",holder.getTextView().toString());
                datos.putString("precios",holder.getTextView1().toString());
                datos.putString("cantidad",holder.getTextView2().toString());
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,DetalleProductoFragment.class,null,"detail")
                        .addToBackStack("")
                        .commit();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return nomProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textViewNombres;
        private final TextView textViewPrecios;
        private final TextView textViewCantidad;

        public ViewHolder(View layout)
        {
            super(layout);
            // Define click listener for the ViewHolder's View

            textViewNombres = (TextView) layout.findViewById(R.id.textViewNombres);
            textViewPrecios = (TextView) layout.findViewById(R.id.textViewPrecios);
            textViewCantidad = (TextView) layout.findViewById(R.id.textViewCantidad);
        }

        public TextView getTextView()
        {
            return textViewNombres;
        }
        public TextView getTextView1()
        {
            return textViewPrecios;
        }
        public TextView getTextView2()
        {
            return textViewCantidad;
        }
    }

}
