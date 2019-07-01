package com.example.ankyadityap.coursepedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisActivity extends AppCompatActivity {
    private ImageView imageView;
    private Animation smalltobig, btta, btta2;
    private TextView textView;
    private Button button;
    private EditText inputNama, inputNim, inputPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        // load this animation
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig2);
        btta = AnimationUtils.loadAnimation(this, R.anim.btta);
        btta2 = AnimationUtils.loadAnimation(this, R.anim.btta2);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        inputNama = (EditText) findViewById(R.id.regisNama);
        inputNim = (EditText) findViewById(R.id.regisNim);
        inputPassword = (EditText) findViewById(R.id.regisPassword);
        button = (Button) findViewById(R.id.button);
        loadingBar = new ProgressDialog(this);

        // passing animation and start it
        imageView.startAnimation(smalltobig);

        textView.startAnimation(btta);

        button.startAnimation(btta2);

        inputNama.startAnimation(btta2);
        inputNim.startAnimation(btta2);
        inputPassword.startAnimation(btta2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount(){
        String name = inputNama.getText().toString();
        String nim = inputNim.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(nim)) {
            Toast.makeText(this, "Please write your NIM", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }else{
            loadingBar.setTitle("Create Accont");
            loadingBar.setMessage("Please wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateNIM(name, nim, password);
        }
    }

    private void ValidateNIM(final String name, final String nim, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(nim).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("nim",nim);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);

                    RootRef.child("Users").child(nim).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(RegisActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }else{
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisActivity.this, "Network Error: Please try again some time", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else{
                    Toast.makeText(RegisActivity.this,"This "+nim+" Already Exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RegisActivity.this, "Please try again using another NIM", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
