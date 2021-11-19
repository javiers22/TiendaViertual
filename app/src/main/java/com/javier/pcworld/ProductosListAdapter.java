package com.javier.pcworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductosListAdapter extends RecyclerView.Adapter<ProductosListAdapter.ViewHolder>
{
    AppCompatActivity context;
    Bundle info;
    ArrayList<String> nomProductos;
    ArrayList<String> preciosProductos;
    ArrayList<String> cantidadProductos;
    ArrayList<String> idProductos;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textViewNombres;
        private final TextView textViewPrecios;
        private final TextView textViewCantidad;
        private final TextView textViewId;

        public ViewHolder(View layout)
        {
            super(layout);
            // Define click listener for the ViewHolder's View

            textViewNombres = (TextView) layout.findViewById(R.id.textViewNombres);
            textViewPrecios = (TextView) layout.findViewById(R.id.textViewPrecios);
            textViewCantidad = (TextView) layout.findViewById(R.id.textViewCantidad);
            textViewId = (TextView) layout.findViewById(R.id.textViewId);
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
        public TextView getTextView3()
        {
            return textViewId;
        }
    }

    public ProductosListAdapter(AppCompatActivity context, Bundle info)
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_list_items, parent, false);
        return new ViewHolder(myViewHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.getTextView().setText("nombre: "+nomProductos.get(position));
        holder.getTextView1().setText("precio: "+preciosProductos.get(position));
        holder.getTextView2().setText("cantidad: "+cantidadProductos.get(position));
        holder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(),holder.getTextView().getText(),Toast.LENGTH_LONG).show();
                Bundle datos=new Bundle();
                datos.putString("nombres",holder.getTextView().toString());
                datos.putString("precios",holder.getTextView1().toString());
                datos.putString("cantidad",holder.getTextView2().toString());
                Variables.id_product=holder.getTextView3().getText().toString();
                Variables.name_product=holder.getTextView().getText().toString();
                Variables.cant_product=holder.getTextView2().getText().toString();
                Variables.price_product=holder.getTextView1().getText().toString();
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,DetalleProductoFragment.class,null,"detail")
                        .addToBackStack("")
                        .commit();
                /*context.getSupportFragmentManager()
                        .beginTransaction()
                        .remove(context.getSupportFragmentManager().findFragmentById(R.id.fragment_container_view))
                        .commit();*/
                /*Intent intento=new Intent(context,MainActivity.class);
                context.startActivity(intento);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return nomProductos.size();
    }
}
