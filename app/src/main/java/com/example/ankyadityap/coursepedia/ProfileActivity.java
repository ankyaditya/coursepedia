package com.example.ankyadityap.coursepedia;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ProfileActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, completeName, userName, nim, expired;
    Button btnEdit, btnLogout, btnMyQuestion;
    CircleImageView profileImageView;

    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePrictureRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        storageProfilePrictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        completeName = findViewById(R.id.showName);
        expired = findViewById(R.id.expired);

        userName = (TextView) findViewById(R.id.showName);
        nim = (TextView) findViewById(R.id.showNim);
        profileImageView = (CircleImageView) findViewById(R.id.showImageUser);

        btnEdit = findViewById(R.id.btnEdit);
        btnLogout = findViewById(R.id.btnLogout);
        btnMyQuestion = findViewById(R.id.btnMyQuestion);


        //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        //custom font
        titlepage.setTypeface(MRegular);
        subtitlepage.setTypeface(MLight);
        completeName.setTypeface(MRegular);
        userName.setTypeface(MRegular);

        nim.setTypeface(MMedium);
        expired.setTypeface(MLight);
        btnEdit.setTypeface(MMedium);

        userName.setText(Prevalent.currentOnlineUser.getName());
        nim.setText(Prevalent.currentOnlineUser.getNim());
        Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bring to user to another activity
                Intent a = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(a);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent. FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnMyQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(ProfileActivity.this,MyQuestionActivity.class);
                startActivity(a);
            }
        });

    }
}
