package com.example.ankyadityap.coursepedia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MateriActivity extends AppCompatActivity {

    public String pdfname;
    TextView textView2, ivItemOneTitle, ivItemOneSubTitle, ivItemTwoTitle,
            ivItemTwoSubTitle, ivItemThreeTitle, ivItemThreeSubTitle;

    Button btnGet;

    LinearLayout ivItemOne, ivItemTwo, ivItemThree;

    ImageView ivIlls;
    Animation smalltobig, stb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        stb2 = AnimationUtils.loadAnimation(this, R.anim.stb2);

        textView2 = (TextView) findViewById(R.id.textView2);
        ivItemOneTitle = (TextView) findViewById(R.id.ivItemOneTitle);
        ivItemOneSubTitle = (TextView) findViewById(R.id.ivItemOneSubTitle);
        ivItemTwoTitle = (TextView) findViewById(R.id.ivItemTwoTitle);
        ivItemTwoSubTitle = (TextView) findViewById(R.id.ivItemTwoSubTitle);
        ivItemThreeTitle = (TextView) findViewById(R.id.ivItemThreeTitle);
        ivItemThreeSubTitle = (TextView) findViewById(R.id.ivItemThreeSubTitle);

        ivItemOne = (LinearLayout) findViewById(R.id.ivItemOne);
        ivItemTwo = (LinearLayout) findViewById(R.id.ivItemTwo);
        ivItemThree = (LinearLayout) findViewById(R.id.ivItemThree);

        btnGet =(Button) findViewById(R.id.btnGet);


        ivIlls = (ImageView) findViewById(R.id.ivIlls);

        Typeface mlight = Typeface.createFromAsset(getAssets(), "fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(), "fonts/MontserratRegular.ttf");


        textView2.setTypeface(mlight);
        ivItemOneTitle.setTypeface(mregular);
        ivItemTwoTitle.setTypeface(mregular);
        ivItemThreeTitle.setTypeface(mregular);
        ivItemOneSubTitle.setTypeface(mlight);
        ivItemOneSubTitle.setTypeface(mlight);
        ivItemTwoSubTitle.setTypeface(mlight);
        ivItemThreeSubTitle.setTypeface(mlight);
        btnGet.setTypeface(mmedium);

        textView2.setTranslationY(400);

        btnGet.setTranslationY(400);

        ivItemOne.setTranslationX(800);
        ivItemTwo.setTranslationX(800);
        ivItemThree.setTranslationX(800);


        btnGet.setAlpha(0);

        textView2.setAlpha(0);

        ivItemOne.setAlpha(0);
        ivItemTwo.setAlpha(0);
        ivItemThree.setAlpha(0);

        btnGet.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();

        textView2.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();

        ivItemOne.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        ivItemTwo.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        ivItemThree.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();

        ivIlls.startAnimation(stb2);

        ivItemOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kalkulus = "kalkulus.pdf";
                Intent a = new Intent(MateriActivity.this,PdfActivity.class);
                a.putExtra("pdf", kalkulus);
                startActivity(a);
            }
        });

        ivItemTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fisika = "fisika.pdf";
                Intent a = new Intent(MateriActivity.this,PdfActivity.class);
                a.putExtra("pdf", fisika);
                startActivity(a);
            }
        });

        ivItemThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kimia = "kimia.pdf";
                Intent a = new Intent(MateriActivity.this,PdfActivity.class);
                a.putExtra("pdf", kimia);
                startActivity(a);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MateriActivity.this,MainActivity.class);
                startActivity(a);
            }
        });



    }
}
