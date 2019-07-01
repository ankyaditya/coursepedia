package com.example.ankyadityap.coursepedia.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ankyadityap.coursepedia.Interface.ItemClickListener;
import com.example.ankyadityap.coursepedia.R;

public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNameScore, txtscoreScore;
    private ItemClickListener itemClickListener;

    public ScoreViewHolder(View itemView) {
        super(itemView);

        txtNameScore = itemView.findViewById(R.id.nameScore);
        txtscoreScore = itemView.findViewById(R.id.scoreScore);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
