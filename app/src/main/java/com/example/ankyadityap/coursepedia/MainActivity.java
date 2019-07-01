package com.example.ankyadityap.coursepedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference ProductsRef;

    TextView nameuser, nimuser, review, network, plugins, myapps, mainmenus,
            pagetitle, pagesubtitle;

    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3, btheory, btnconsult, btnquiz, btnrank;
    CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        nameuser = findViewById(R.id.nameuser);
        nimuser = findViewById(R.id.nimuser);

        imageView3 = findViewById(R.id.imageView3);
        profileImage = findViewById(R.id.userProfileImage);

        review = findViewById(R.id.review);
        network = findViewById(R.id.network);
        plugins = findViewById(R.id.plugins);
        myapps = findViewById(R.id.myapps);
        mainmenus = findViewById(R.id.mainmenus);

        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        btheory = findViewById(R.id.btnTheory);
        btnconsult = findViewById(R.id.btnConsult);
        btnguide = findViewById(R.id.btnguide);
        btnquiz = findViewById(R.id.btnQuiz);
        btnrank = findViewById(R.id.btnRank);

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        btnguide.startAnimation(atgthree);

        TextView userNameTextView = findViewById(R.id.nameuser);
        TextView userNimTextView = findViewById(R.id.nimuser);
        CircleImageView profileImageView = findViewById(R.id.userProfileImage);

        userNameTextView.setText(Prevalent.currentOnlineUser.getName());
        userNimTextView.setText(Prevalent.currentOnlineUser.getNim());
        Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,PackageActivity.class);
                startActivity(a);
            }
        });

        btheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,MateriActivity.class);
                startActivity(a);
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(a);
            }
        });

        btnconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,ConsultActivity.class);
                startActivity(a);
            }
        });

        btnquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,QuizActivity.class);
                startActivity(a);
            }
        });

        btnrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this,RankActivity.class);
                startActivity(a);
            }
        });

    }
}
