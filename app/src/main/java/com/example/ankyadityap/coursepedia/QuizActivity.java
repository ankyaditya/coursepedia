package com.example.ankyadityap.coursepedia;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private ViewPager mSlideaViewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter slideAdapter;

    private TextView[] mDots;

    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mSlideaViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextButton);
        mBackBtn = (Button) findViewById(R.id.prevButton);

        slideAdapter = new SlideAdapter(this);

        mSlideaViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);
        mSlideaViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct=0;
                int wrong=0;
                int score= 0;
                Question library = new Question();

                if (mNextBtn.getText().toString().equals("Finish")&&slideAdapter.sum==5){

                    Intent i = new Intent(QuizActivity.this,ResultActivity.class);

                    for (int j=0; j<=slideAdapter.sum-1; j++){
                        if (slideAdapter.jawab[j].equals(library.getAnswer(j))){
                            correct++;
                        }
                        else if(slideAdapter.jawab[j].equals(null)){

                            correct=correct;
                            wrong=wrong;
                        }
                        else {
                            wrong++;

                        }
                    }

                    score = correct * 50;
                    i.putExtra("total",String.valueOf(slideAdapter.sum));
                    i.putExtra("correct",String.valueOf(correct));
                    i.putExtra("incorrect",String.valueOf(wrong));
                    i.putExtra("score",String.valueOf(Integer.toString(score)));
                    startActivity(i);

                }
                mSlideaViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideaViewPager.setCurrentItem(mCurrentPage-1);

            }
        });



    }

    public void addDotsIndicator(int position){


        mDots = new TextView[5];
        mDotLayout.removeAllViews();


        for (int i = 0;i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);

        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;

            if(i==0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");
            }else if (i == mDots.length -1){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");

            }else{
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
