package com.example.ankyadityap.coursepedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankyadityap.coursepedia.Model.Users;
import com.example.ankyadityap.coursepedia.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity {

    TextView ivLogo, ivSubtitle, ivBtn;
    ImageView ivSplash;
    Animation smalltobig, fleft, fhelper;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        fleft = AnimationUtils.loadAnimation(this, R.anim.fleft);
        fhelper = AnimationUtils.loadAnimation(this, R.anim.fhelper);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        Typeface logox = Typeface.createFromAsset(getAssets(), "fonts/Fredoka.ttf");
        Typeface mlight = Typeface.createFromAsset(getAssets(), "fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(), "fonts/MontserratRegular.ttf");


        ivLogo = (TextView) findViewById(R.id.ivLogo);
        ivSubtitle = (TextView) findViewById(R.id.ivSubtitle);
        ivBtn = (TextView) findViewById(R.id.ivBtn);

        ivSplash = (ImageView) findViewById(R.id.ivSplash);

        ivLogo.setTypeface(logox);
        ivSubtitle.setTypeface(mlight);
        ivBtn.setTypeface(mmedium);

        ivSplash.startAnimation(smalltobig);

        ivLogo.setTranslationX(400);
        ivSubtitle.setTranslationX(400);
        ivBtn.setTranslationX(400);

        ivLogo.setAlpha(0);
        ivSubtitle.setAlpha(0);
        ivBtn.setAlpha(0);

        ivLogo.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ivSubtitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ax = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(ax);
                overridePendingTransition(R.anim.fleft, R.anim.fhelper);
            }
        });

        String UserNimKey = Paper.book().read(Prevalent.UserNimKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if(UserNimKey != "" && UserPasswordKey != ""){
            if(!TextUtils.isEmpty(UserNimKey) && !TextUtils.isEmpty(UserPasswordKey)){
                AllowAccess(UserNimKey, UserPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }


    }

    private void AllowAccess(final String nim, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(nim).exists()){
                    Users usersData = dataSnapshot.child("Users").child(nim).getValue(Users.class);

                    if(usersData.getNim().equals(nim)){
                        if(usersData.getPassword().equals(password)){
                            Toast.makeText(SplashActivity.this, "Log in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);
                        }else{
                            Toast.makeText(SplashActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }

                }else{
                    Toast.makeText(SplashActivity.this, "Account with this " +nim+"don't exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

