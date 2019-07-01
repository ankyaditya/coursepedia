package com.example.ankyadityap.coursepedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    private DatabaseReference ScoreRef, UserRef;
    private ProgressDialog loadingBar;
    public String nilai;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ScoreRef = FirebaseDatabase.getInstance().getReference().child("Score");
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        t1=(TextView)findViewById(R.id.textView5);
        t2=(TextView)findViewById(R.id.textView6);
        t3=(TextView)findViewById(R.id.textView7);
        t4=(TextView)findViewById(R.id.textView8);

        finish = findViewById(R.id.btnFinish);
        loadingBar = new ProgressDialog(this);

        Intent i=getIntent();

        String question = i.getStringExtra("total");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("incorrect");
        nilai = i.getStringExtra("score");

        t1.setText(question);
        t2.setText(correct);
        t3.setText(wrong);
        t4.setText(nilai);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveScoreToDatabase();
            }
        });

    }

    private void SaveScoreToDatabase() {

        final HashMap<String, Object> scoreMap = new HashMap<>();
        scoreMap.put("score", nilai);

        final HashMap<String, Object> rankMap = new HashMap<>();
        rankMap.put("score", nilai);
        rankMap.put("name", Prevalent.currentOnlineUser.getName());

        UserRef.child(Prevalent.currentOnlineUser.getNim())
                .updateChildren(scoreMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            ScoreRef.child(Prevalent.currentOnlineUser.getNim())
                                    .updateChildren(rankMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                                                startActivity(intent);

                                                loadingBar.dismiss();
                                                Toast.makeText(ResultActivity.this, "Score is added successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(ResultActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
