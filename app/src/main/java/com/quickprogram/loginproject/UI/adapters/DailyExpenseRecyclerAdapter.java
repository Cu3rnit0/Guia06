package com.quickprogram.loginproject.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quickprogram.loginproject.Models.Gastodia;
import com.quickprogram.loginproject.R;
import com.quickprogram.loginproject.UI.viewHolders.DailyExpenseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DailyExpenseRecyclerAdapter extends RecyclerView.Adapter<DailyExpenseViewHolder>{
    List<Gastodia> items = new ArrayList<>();
    public DailyExpenseRecyclerAdapter(List<Gastodia> items) {
        this.items = new ArrayList<>();
    }
    @NonNull
    @Override
    public DailyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.detail_item_layout, parent, false);
        return new DailyExpenseViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull DailyExpenseViewHolder holder, int position) {
        holder.txvNombreArticulo.setText("Nombre: " + items.get(position).getNombreArticulo());
        holder.txvPrecio.setText( "Precio: $" + String.format("%.2f", items.get(position).getPrecio()));
        holder.txvCantidad.setText("Cantidad: " + items.get(position).getCantidad());
        holder.txvSubtotal.setText("Subtotal: $" + String.format("%.2f", items.get(position).getSubTotal()));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setDataList(List<Gastodia> dataList) {
        this.items = dataList;
        notifyDataSetChanged();
    }
    public double getActualSubTotalAll() {
        double finalData = 0;
        for(Gastodia item : this.items) {
            finalData += item.getSubTotal();
        }
        return finalData;
    }
}
