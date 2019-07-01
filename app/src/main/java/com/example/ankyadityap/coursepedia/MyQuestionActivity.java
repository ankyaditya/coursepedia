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
import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.example.ankyadityap.coursepedia.ViewHolder.QuestionViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyQuestionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);

        recyclerView = findViewById(R.id.question_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference questionListRef = FirebaseDatabase.getInstance().getReference().child("Question List");

        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                .setQuery(questionListRef.child("mhs view")
                    .child(Prevalent.currentOnlineUser.getNim()).child("Question"), Question.class)
                    .build();

        FirebaseRecyclerAdapter<Question, QuestionViewHolder> adapter
                = new FirebaseRecyclerAdapter<Question, QuestionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull final Question model) {
                holder.txtQuestionID.setText(model.getQid());
                holder.txtQuestionMatkul.setText(model.getMatkul());
                holder.txtQuestionDosen.setText(model.getQdosen());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]{
                                "See Details",
                                "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyQuestionActivity.this);
                        builder.setTitle("Question Options");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0){
                                    Intent intent = new Intent(MyQuestionActivity.this, QuestionDetailActivity.class);
                                    intent.putExtra("qid", model.getQid());
                                    startActivity(intent);
                                }
                                if (i ==1 ){
                                    questionListRef.child("mhs view").child(Prevalent.currentOnlineUser.getNim())
                                            .child("Question").child(model.getQid()).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(MyQuestionActivity.this, "Question removed successfully", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(MyQuestionActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_question_layout, parent, false);
                QuestionViewHolder holder = new QuestionViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
}
