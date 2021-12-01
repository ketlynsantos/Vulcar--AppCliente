package com.app.clientevulcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;

import java.util.ArrayList;

public class RecyclerAdapterVehicles extends RecyclerView.Adapter<RecyclerAdapterVehicles.ViewHolder> {

    int groupid;
    ArrayList<Vehicle> lista = new ArrayList<>();
    Context context;

    public RecyclerAdapterVehicles(Context context, ArrayList<Vehicle> lista) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vehicles, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtId.setText(lista.get(position).getId());
        holder.txtModelo.setText(lista.get(position).getModelo());
        holder.txtMarca.setText(lista.get(position).getMarca());
        holder.txtCor.setText(lista.get(position).getCor());
        holder.txtCategoria.setText(lista.get(position).getCategoria());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtModelo;
        TextView txtMarca;
        TextView txtCor;
        TextView txtCategoria;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txt_id);
            txtModelo = itemView.findViewById(R.id.txt_modelo);
            txtMarca = itemView.findViewById(R.id.txt_marca);
            txtCor = itemView.findViewById(R.id.txt_cor);
            txtCategoria = itemView.findViewById(R.id.txt_categoria);

        }
    }
}
