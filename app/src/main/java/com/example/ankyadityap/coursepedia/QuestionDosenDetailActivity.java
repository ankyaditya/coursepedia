package com.example.ankyadityap.coursepedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ankyadityap.coursepedia.Model.Question;
import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class QuestionDosenDetailActivity extends AppCompatActivity {

    private DatabaseReference AnswerRef;
    private ProgressDialog loadingBar;

    private ImageView questionImage;
    private TextView questionMatkul, questionDosen, questionDesc, questionAnswer;
    private String questionID = "", answer;

    public String questionMhs;
    private Button submitAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_dosen_detail);

        questionImage = (ImageView) findViewById(R.id.viewImageQuestion) ;
        questionMatkul = (TextView) findViewById(R.id.viewMatkul);
        questionDosen = (TextView) findViewById(R.id.viewDosen);
        questionDesc = (TextView) findViewById(R.id.viewQuestion);
        questionAnswer = (TextView) findViewById(R.id.viewAnswer);
        submitAnswer = (Button) findViewById(R.id.btnAnswerQuestion);
        loadingBar = new ProgressDialog(this);

        questionID = getIntent().getStringExtra("qid");

        AnswerRef = FirebaseDatabase.getInstance().getReference().child("Question List");

        getQuestionDetails(questionID);

        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAnswerToDatabase();
            }
        });

    }

    private void getQuestionDetails(String questionID) {

        DatabaseReference questionRef = FirebaseDatabase.getInstance().getReference().child("Question List")
                .child("dosen view").child(Prevalent.currentOnlineUser.getNim())
                .child("Question");

        questionRef.child(questionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Question question = dataSnapshot.getValue(Question.class);

                    questionMhs = question.getMhs();
                    questionMatkul.setText(question.getMatkul());
                    questionDosen.setText(question.getQdosen());
                    questionDesc.setText(question.getDescription());
                    questionAnswer.setText(question.getAnswer());
                    Picasso.get().load(question.getImage()).into(questionImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void SaveAnswerToDatabase()
    {
        answer = questionAnswer.getText().toString();
        final HashMap<String, Object> questionMap = new HashMap<>();
        questionMap.put("answer", answer);

        AnswerRef.child("dosen view").child(Prevalent.currentOnlineUser.getNim())
                .child("Question").child(questionID).updateChildren(questionMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            AnswerRef.child("mhs view").child(questionMhs)
                                    .child("Question").child(questionID).updateChildren(questionMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(QuestionDosenDetailActivity.this, MainDosenActivity.class);
                                                startActivity(intent);

                                                loadingBar.dismiss();
                                                Toast.makeText(QuestionDosenDetailActivity.this, "Question is answered successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(QuestionDosenDetailActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
