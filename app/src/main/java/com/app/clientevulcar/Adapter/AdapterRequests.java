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
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class AdapterRequests extends ArrayAdapter<Services> {
    int groupid;
    ArrayList<Services> lista;
    Context context;

    public AdapterRequests(@NonNull Context context, int vg, int id, ArrayList<Services> lista) {
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
        TextView txtIdLoja = itemView.findViewById(R.id.txt_id_loja);
        TextView txtNameBusiness = itemView.findViewById(R.id.txt_name_business);
        TextView txtNameService = itemView.findViewById(R.id.txt_services);

        txtId.setText(lista.get(position).getId());
        txtIdLoja.setText(lista.get(position).getId_loja());
        txtNameBusiness.setText(lista.get(position).getNome_loja());
        txtNameService.setText(lista.get(position).getNome());

        return itemView;
    }
}
