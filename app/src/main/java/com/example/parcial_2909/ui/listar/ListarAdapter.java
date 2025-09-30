package com.example.parcial_2909.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial_2909.R;
import com.example.parcial_2909.modelo.Producto;

import java.util.List;

public class ListarAdapter extends RecyclerView.Adapter<ListarAdapter.ViewHolderProducto>{
    private List<Producto> listado;
    private Context context;
    private LayoutInflater li;

    public ListarAdapter(List<Producto> listado, Context context, LayoutInflater li) {
        this.listado = listado;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=li.inflate(R.layout.item_producto,parent,false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto p=listado.get(position);
        holder.codigo.setText("CÓDIGO: "+p.getCogido());
        holder.descripcion.setText(p.getDescripcion());
        holder.precio.setText("$ "+String.format("%.2f",p.getPrecio()));
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{
        TextView codigo, descripcion, precio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            codigo=itemView.findViewById(R.id.tvCodigo);
            descripcion=itemView.findViewById(R.id.tvDescripcion);
            precio=itemView.findViewById(R.id.tvPrecio);
        }
    }
}
