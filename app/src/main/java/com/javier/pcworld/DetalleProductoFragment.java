package com.javier.pcworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleProductoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button add;
    Button cancel;

    public DetalleProductoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleProductoFragment newInstance(String param1, String param2) {
        DetalleProductoFragment fragment = new DetalleProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        View fragmento=inflater.inflate(R.layout.fragment_detalle_producto,container,false);
        TextView pregunta=(TextView) fragmento.findViewById(R.id.text_confirm);
        pregunta.setText(getString(R.string.add_car_txt)+" "+Variables.name_product.split(": ")[1]);
        add=(Button)fragmento.findViewById(R.id.add_button);
        cancel=(Button)fragmento.findViewById(R.id.cancel_button);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*consumir api rest historia 8*/
                ArrayList<String>producto=new ArrayList<String>();
                producto.add(Variables.id_product);
                producto.add(Variables.name_product);
                producto.add(Variables.cant_product);
                producto.add(Variables.price_product);
                Variables.id_products.add(producto);
                Toast.makeText(fragmento.getContext(),R.string.correct_register,Toast.LENGTH_LONG).show();
                Intent intento=new Intent(fragmento.getContext(), ProductosActivity.class);
                startActivity(intento);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intento=new Intent(fragmento.getContext(), ProductosActivity.class);
                startActivity(intento);
            }
        });

        return fragmento;
    }
}