package com.app.clientevulcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.clientevulcar.Model.Services;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class AdapterServices extends ArrayAdapter<Services> {

    int groupid;
    ArrayList<Services> lista;
    Context context;

    public AdapterServices(@NonNull Context context, int vg, int id, ArrayList<Services> lista) {
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

        TextView txtId = itemView.findViewById(R.id.txt_id_services);
        TextView txtName = itemView.findViewById(R.id.txt_name_business);
        TextView txtCategory = itemView.findViewById(R.id.txt_category_services);
        TextView txtDesc = itemView.findViewById(R.id.txt_desc_services);
        TextView txtPrice = itemView.findViewById(R.id.txt_price_services);

        txtId.setText(lista.get(position).getId());
        txtName.setText(lista.get(position).getNome());
        txtCategory.setText(lista.get(position).getCategoria());
        txtDesc.setText(lista.get(position).getDesc());
        txtPrice.setText(lista.get(position).getValor().toString());

        return itemView;
    }
}
