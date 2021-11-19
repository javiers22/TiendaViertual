package com.javier.pcworld;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView listRecicleView;
    RecyclerView.Adapter<ProductosListAdapter.ViewHolder> myAdapter;

    public ProductosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductosFragment newInstance(String param1, String param2) {
        ProductosFragment fragment = new ProductosFragment();
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
        //return inflater.inflate(R.layout.fragment_productos, container, false);
        View fragmento=inflater.inflate(R.layout.fragment_productos,container,false);
        return fragmento;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState) {
    {
        super.onViewCreated(view,savedInstaceState);

        /*consumir api rest de productos historia 6*/

        ArrayList<String> nomProductos=new ArrayList<String>();
        nomProductos.add("PC Acer");
        nomProductos.add("PC HP");
        nomProductos.add("PC MAC");

        ArrayList<String> preciosProductos=new ArrayList<String>();
        preciosProductos.add("100");
        preciosProductos.add("200");
        preciosProductos.add("300");

        ArrayList<String> cantidadProductos=new ArrayList<String>();
        cantidadProductos.add("3");
        cantidadProductos.add("3");
        cantidadProductos.add("3");

        ArrayList<String> idProductos=new ArrayList<String>();
        idProductos.add("1");
        idProductos.add("2");
        idProductos.add("3");

        Bundle info=new Bundle();
        info.putStringArrayList("nombres",nomProductos);
        info.putStringArrayList("precios",preciosProductos);
        info.putStringArrayList("cantidad",cantidadProductos);
        info.putStringArrayList("id",idProductos);

        listRecicleView=requireView().findViewById(R.id.recyclerProductosList);
        myAdapter=new ProductosListAdapter((AppCompatActivity) getActivity(),info);

        listRecicleView.setHasFixedSize(true);
        listRecicleView.setAdapter(myAdapter);
        listRecicleView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }
}
}