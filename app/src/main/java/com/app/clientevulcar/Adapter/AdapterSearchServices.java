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

public class AdapterSearchServices extends ArrayAdapter<Services> {
    int groupid;
    ArrayList<Services> lista;
    Context context;

    public AdapterSearchServices(@NonNull Context context, int vg, int id, ArrayList<Services> lista) {
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

        TextView txtIdService = itemView.findViewById(R.id.txt_id_services);
        TextView txtNameService = itemView.findViewById(R.id.txt_name_services);
        TextView txtDescServices = itemView.findViewById(R.id.txt_desc_services);
        TextView txtIdBusiness = itemView.findViewById(R.id.txt_id_loja);
        TextView txtNameBusiness = itemView.findViewById(R.id.txt_name_business);
        TextView txtValue = itemView.findViewById(R.id.txt_value);
        TextView txtIdCategory = itemView.findViewById(R.id.txt_id_category);
        TextView txtCategory = itemView.findViewById(R.id.txt_name_category);

        txtIdService.setText(lista.get(position).getId());
        txtNameService.setText(lista.get(position).getNome());
        txtDescServices.setText(lista.get(position).getDesc());
        txtIdBusiness.setText(lista.get(position).getId_loja());
        txtNameBusiness.setText(lista.get(position).getNome_loja());
        txtValue.setText(lista.get(position).getValor());
        txtIdCategory.setText(lista.get(position).getId_categoria());
        txtCategory.setText(lista.get(position).getCategoria());

        return itemView;
    }
}
