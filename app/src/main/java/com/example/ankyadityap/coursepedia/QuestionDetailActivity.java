package com.example.ankyadityap.coursepedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ankyadityap.coursepedia.Model.Question;
import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class QuestionDetailActivity extends AppCompatActivity {

    private ImageView questionImage;
    private TextView questionMatkul, questionDosen, questionDesc, questionAnswer;
    private String questionID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        questionImage = (ImageView) findViewById(R.id.viewImageQuestion) ;
        questionMatkul = (TextView) findViewById(R.id.viewMatkul);
        questionDosen = (TextView) findViewById(R.id.viewDosen);
        questionDesc = (TextView) findViewById(R.id.viewQuestion);
        questionAnswer = (TextView) findViewById(R.id.viewAnswer);

        questionID = getIntent().getStringExtra("qid");

        getQuestionDetails(questionID);

    }

    private void getQuestionDetails(String questionID) {

        DatabaseReference questionRef = FirebaseDatabase.getInstance().getReference().child("Question List")
                .child("mhs view").child(Prevalent.currentOnlineUser.getNim())
                .child("Question");

        questionRef.child(questionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Question question = dataSnapshot.getValue(Question.class);

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
}
