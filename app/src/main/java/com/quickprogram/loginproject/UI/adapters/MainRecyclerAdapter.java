package com.quickprogram.loginproject.UI.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quickprogram.loginproject.Dtos.section;
import com.quickprogram.loginproject.Models.Presupuesto;
import com.quickprogram.loginproject.UI.viewHolders.MainViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder> {
    List<section> sectionList;
    private List<Presupuesto> sectionItems;
    private final ChilRecyclerAdapter.OnItemChildClickListener onChildItemClickListener;
    public MainRecyclerAdapter(List<Presupuesto> sectionItems, ChilRecyclerAdapter.OnItemChildClickListener
            itemClickListener) {
        this.sectionItems = sectionItems;
        this.onChildItemClickListener = itemClickListener;
        this.sectionList = new ArrayList<>();
        sectionList.add(new section("ACTIVAS", onlyActiveBudget() ));
        sectionList.add(new section("COMPLETADAS", onlyNonActiveBudget() )); }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.section_row, parent, false);
        return new MainViewHolder(v);}
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        section section = sectionList.get(position);
        String sectionName = section.getSectionName();
        List<Presupuesto> items = new ArrayList<>();
        switch (sectionName) {
            case "ACTIVAS":
                items = onlyActiveBudget();
                break;
            case "COMPLETADAS":
                items = onlyNonActiveBudget();
                break;
        }
        holder.sectionName.setText(sectionName);
        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items, onChildItemClickListener);
        holder.childRcv.setAdapter(childRecyclerAdapter);
    }
    @Override
    public int getItemCount() {
        return sectionList.size();
    }
    public void setDataList(List<Presupuesto> dataList) {
        this.sectionItems = dataList;
        notifyDataSetChanged();
    }
    private List<Presupuesto> onlyActiveBudget(){
        return sectionItems.stream()
                .filter(Presupuesto::isActivo)
                .collect(Collectors.toList());
    }
    private List<Presupuesto> onlyNonActiveBudget(){
        return sectionItems.stream()
                .filter(budget -> !budget.isActivo())
                .collect(Collectors.toList());
    }
}
