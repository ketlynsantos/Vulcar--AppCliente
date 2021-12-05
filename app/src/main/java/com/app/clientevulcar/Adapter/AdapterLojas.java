package com.app.clientevulcar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.clientevulcar.Model.Business;
import com.app.clientevulcar.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterLojas extends ArrayAdapter<Business> {
    int groupid;
    ArrayList<Business> lista;
    Context context;

    public AdapterLojas(@NonNull Context context, int vg, int id, ArrayList<Business> lista) {
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
        ImageView imgBus = itemView.findViewById(R.id.img_bus);
        TextView txtName = itemView.findViewById(R.id.txt_name);
        TextView txtId = itemView.findViewById(R.id.txt_id);

       // imgBus.setImageResource(lista.get(position).getImg());
        txtName.setText(lista.get(position).getNome());
        txtId.setText(lista.get(position).getId());

//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return itemView;
    }

}
