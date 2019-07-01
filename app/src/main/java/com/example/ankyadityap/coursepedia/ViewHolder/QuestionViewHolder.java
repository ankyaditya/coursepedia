package com.example.ankyadityap.coursepedia.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ankyadityap.coursepedia.Interface.ItemClickListener;
import com.example.ankyadityap.coursepedia.R;

public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtQuestionDosen, txtQuestionMatkul, txtQuestionID;
    private ItemClickListener itemClickListener;

    public QuestionViewHolder(View itemView) {
        super(itemView);

        txtQuestionDosen = itemView.findViewById(R.id.question_dosen7);
        txtQuestionMatkul = itemView.findViewById(R.id.question_matkul7);
        txtQuestionID = itemView.findViewById(R.id.question_id7);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}

