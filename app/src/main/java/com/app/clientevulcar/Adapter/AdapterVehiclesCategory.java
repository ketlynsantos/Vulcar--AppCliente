package com.app.clientevulcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.clientevulcar.Model.Business;
import com.app.clientevulcar.Model.Services;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class AdapterVehiclesCategory extends ArrayAdapter<Vehicle> {

    int groupid;
    ArrayList<Vehicle> lista;
    Context context;
    int checkedButtonPosition;

    public AdapterVehiclesCategory(@NonNull Context context, int vg, int id, ArrayList<Vehicle> lista) {
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

        //ImageView imgBus = itemView.findViewById(R.id.img_bus);
        TextView txtVehicles = itemView.findViewById(R.id.txt_modelo);
        TextView txtId = itemView.findViewById(R.id.txt_id);

        // imgBus.setImageResource(lista.get(position).getImg());
        txtId.setText(lista.get(position).getId());
        txtVehicles.setText(lista.get(position).getModelo());

        return itemView;
    }
}
