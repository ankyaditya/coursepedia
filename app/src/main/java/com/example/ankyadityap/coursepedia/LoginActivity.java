package com.example.ankyadityap.coursepedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private ImageView imageView;
    private Animation smalltobig, btta, btta2;
    private TextView textView, subtitle_header, regis, linkdosen;
    private Button button;
    private EditText inputNim, inputPassword;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // load this animation
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig2);
        btta = AnimationUtils.loadAnimation(this, R.anim.btta);
        btta2 = AnimationUtils.loadAnimation(this, R.anim.btta2);

        imageView = findViewById(R.id.imageView);

        regis = findViewById(R.id.textView3);
        textView = findViewById(R.id.textView);
        subtitle_header = findViewById(R.id.subtitle_header);
        linkdosen = findViewById(R.id.linkDosen);

        inputNim = (EditText) findViewById(R.id.loginNim);
        inputPassword = (EditText) findViewById(R.id.loginPassword);
        button = (Button) findViewById(R.id.button);
        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        loadingBar = new ProgressDialog(this);

        // passing animation and start it
        imageView.startAnimation(smalltobig);

        textView.startAnimation(btta);
        subtitle_header.startAnimation(btta);

        button.startAnimation(btta2);

        inputNim.startAnimation(btta2);
        inputPassword.startAnimation(btta2);
        linkdosen.startAnimation(btta2);
        chkBoxRememberMe.startAnimation(btta2);

        Paper.init(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActivity.this,RegisActivity.class);
                startActivity(a);
            }
        });

        linkdosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((linkdosen.getText()).equals("Dosen?")){
                    parentDbName = "Dosen";
                    inputNim.setHint("Kode Dosen");
                    linkdosen.setText("Mahasiswa?");
                }else{
                    parentDbName = "Users";
                    inputNim.setHint("NIM");
                    linkdosen.setText("Dosen?");
                }

            }
        });
    }

    private void LoginUser(){
        String nim = inputNim.getText().toString();
        String password = inputPassword.getText().toString();
        if(TextUtils.isEmpty(nim)) {
            Toast.makeText(this, "Please write your NIM", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }else{
            loadingBar.setTitle("Login Accont");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(nim, password);
        }
    }

    private void AllowAccessToAccount(final String nim, final String password) {
        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserNimKey, nim);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(nim).exists()){

                    Users usersData = dataSnapshot.child(parentDbName).child(nim).getValue(Users.class);

                    if(usersData.getNim().equals(nim)){
                        if(usersData.getPassword().equals(password)){
                            if(parentDbName.equals("Users")){
                                Toast.makeText(LoginActivity.this, "Log in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Log in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainDosenActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Account with this " +nim+" don't exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
