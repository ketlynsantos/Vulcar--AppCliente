package com.app.clientevulcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.clientevulcar.Model.Business;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class AdapterVehicles extends ArrayAdapter<Vehicle> {
    int groupid;
    ArrayList<Vehicle> lista;
    Context context;

    public AdapterVehicles(@NonNull Context context, int vg, int id, ArrayList<Vehicle> lista) {
        super(context, vg, id, lista);
        this.context = context;
        this.groupid = vg;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(groupid, parent, false);
        TextView txtId = itemView.findViewById(R.id.txt_id);
        TextView txtModelo = itemView.findViewById(R.id.txt_modelo);
        TextView txtMarca = itemView.findViewById(R.id.txt_marca);
        TextView txtCor = itemView.findViewById(R.id.txt_cor);
        TextView txtCategoria = itemView.findViewById(R.id.txt_categoria);
        txtId.setText(lista.get(position).getId());
        txtModelo.setText(lista.get(position).getModelo());
        txtMarca.setText(lista.get(position).getMarca());
        txtCor.setText(lista.get(position).getCor());
        txtCategoria.setText(lista.get(position).getCategoria());
        return itemView;
    }
}
