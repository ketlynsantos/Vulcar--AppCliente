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
import com.app.clientevulcar.Model.Services;
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class AdapterSearchBusiness extends ArrayAdapter<Business> {
    int groupid;
    ArrayList<Business> lista;
    Context context;

    public AdapterSearchBusiness(@NonNull Context context, int vg, int id, ArrayList<Business> lista) {
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

        TextView txtIdLoja = itemView.findViewById(R.id.txt_id);
        TextView txtLojaNome = itemView.findViewById(R.id.txt_name);

        txtIdLoja.setText(lista.get(position).getId());
        txtLojaNome.setText(lista.get(position).getNome());

        return itemView;
    }
}
