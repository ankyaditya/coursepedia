package com.example.ankyadityap.coursepedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ankyadityap.coursepedia.Model.Question;
import com.example.ankyadityap.coursepedia.Model.Score;
import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.example.ankyadityap.coursepedia.ViewHolder.QuestionViewHolder;
import com.example.ankyadityap.coursepedia.ViewHolder.ScoreViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RankActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerView = findViewById(R.id.question_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference scoreListRef = FirebaseDatabase.getInstance().getReference();
                //.child("Score");

        FirebaseRecyclerOptions<com.example.ankyadityap.coursepedia.Model.Score> options =
                new FirebaseRecyclerOptions.Builder<com.example.ankyadityap.coursepedia.Model.Score>()
                        .setQuery(scoreListRef.child("Score"), com.example.ankyadityap.coursepedia.Model.Score.class)
                        .build();

        FirebaseRecyclerAdapter<com.example.ankyadityap.coursepedia.Model.Score, ScoreViewHolder> adapter
                = new FirebaseRecyclerAdapter<com.example.ankyadityap.coursepedia.Model.Score, ScoreViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ScoreViewHolder holder, int position, @NonNull final Score model) {
                holder.txtNameScore.setText(model.getName());
                holder.txtscoreScore.setText(model.getScore());
            }

            @NonNull
            @Override
            public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_layout, parent, false);
                ScoreViewHolder holder = new ScoreViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
