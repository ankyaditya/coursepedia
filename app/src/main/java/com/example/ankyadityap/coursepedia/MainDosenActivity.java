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

public class MainDosenActivity extends AppCompatActivity {

    private DatabaseReference ProductsRef;

    TextView namedosen, nimdosen, review, network, plugins, myapps, mainmenus,
            pagetitle, pagesubtitle;

    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3, btnsetting, btnconsult;
    CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dosen);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        namedosen = findViewById(R.id.namedosen);
        nimdosen = findViewById(R.id.nimdosen);

        imageView3 = findViewById(R.id.imageView3);
        profileImage = findViewById(R.id.userProfileImage);

        network = findViewById(R.id.network);
        plugins = findViewById(R.id.plugins);
        mainmenus = findViewById(R.id.mainmenus);

        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);

        // pass an animation
        imageView3.startAnimation(atg);

        pagetitle.startAnimation(atgtwo);
        pagesubtitle.startAnimation(atgtwo);

        TextView userNameTextView = findViewById(R.id.namedosen);
        TextView userNimTextView = findViewById(R.id.nimdosen);
        CircleImageView profileImageView = findViewById(R.id.userProfileImage);

        userNameTextView.setText(Prevalent.currentOnlineUser.getName());
        userNimTextView.setText(Prevalent.currentOnlineUser.getNim());
        Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        pagesubtitle.setText(Prevalent.currentOnlineUser.getName());

        btnconsult = findViewById(R.id.btnConsultDosen);
        btnsetting = findViewById(R.id.btnSettingDosen);

        btnconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainDosenActivity.this,QuestionDosenActivity.class);
                startActivity(a);
            }
        });

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainDosenActivity.this,ProfileDosenActivity.class);
                startActivity(a);
            }
        });
    }
}
