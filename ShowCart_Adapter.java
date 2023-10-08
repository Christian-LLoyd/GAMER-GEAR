package com.example.gamegearhaven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ShowCart_Adapter extends RecyclerView.Adapter<ShowCart_Adapter.ViewHolder> {
    private Context context;
    private List<ShowCart_Model> showCart_modelList;
    public ShowCart_Adapter(Context context, List<ShowCart_Model> showCart_models){

        this.context = context;
        this.showCart_modelList = showCart_models;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_order,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowCart_Model showCart_model = showCart_modelList.get(position);
        holder.title.setText(showCart_model.getTitle());
        holder.price.setText(showCart_model.getPrice());

    }

    @Override
    public int getItemCount() {
        return showCart_modelList.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }
}
