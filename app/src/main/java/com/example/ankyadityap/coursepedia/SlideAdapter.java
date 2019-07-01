package com.example.ankyadityap.coursepedia;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {


    public String[] jawab= {"0","0","0","0","0","0"};
    public int correct =0;
    public int wrong=0;
    public int score=0;
    public TextView jml;
    public int sum= 0;
    public int[] sudah= new int[10];
    Question library = new Question();
    MainActivity text = new MainActivity();
    final int[] a = new int[6];

    Drawable green;

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }


    //Arrays

/*    public String getChoice1 (int nomor)
    {
        String pilih=answer[nomor][0];
        return pilih;
    }
    public String getChoice2 (int nomor)
    {
        String pilih=answer[nomor][1];
        return pilih;
    }
    public String getChoice3 (int nomor)
    {
        String pilih=answer[nomor][2];
        return pilih;
    }
    public String getChoice4 (int nomor)
    {
        String pilih=answer[nomor][3];
        return pilih;
    }
*/

    @Override
    public int getCount() {
        return library.answer.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView slidePertanyaan = (TextView) view.findViewById(R.id.questionsTxt);
        final Button slideButton1 = (Button) view.findViewById(R.id.button1);
        final Button slideButton2 = (Button) view.findViewById(R.id.button2);
        final Button slideButton3 = (Button) view.findViewById(R.id.button3);
        final Button slideButton4 = (Button) view.findViewById(R.id.button4);


        if (position<sum){
            sum--;
        }
        sum++;

        TextView total = (TextView) view.findViewById(R.id.textView5);
        TextView benar = (TextView) view.findViewById(R.id.textView6);
        TextView salah = (TextView) view.findViewById(R.id.textView7);
        TextView poin = (TextView) view.findViewById(R.id.textView8);


        slidePertanyaan.setText(library.slide_pertanyaan[position]);

        slideButton1.setText(library.answer[position][0]);
        slideButton2.setText(library.answer[position][1]);
        slideButton3.setText(library.answer[position][2]);
        slideButton4.setText(library.answer[position][3]);

        if (sudah[position]==1){
            slideButton1.setBackgroundResource(R.drawable.btngreen);
            slideButton2.setBackgroundResource(R.drawable.btnblue);
            slideButton3.setBackgroundResource(R.drawable.btnblue);
            slideButton4.setBackgroundResource(R.drawable.btnblue);
        }
        else if (sudah[position]==2){
            slideButton2.setBackgroundResource(R.drawable.btngreen);
            slideButton1.setBackgroundResource(R.drawable.btnblue);
            slideButton3.setBackgroundResource(R.drawable.btnblue);
            slideButton4.setBackgroundResource(R.drawable.btnblue);
        }
        else if (sudah[position]==3){
            slideButton3.setBackgroundResource(R.drawable.btngreen);
            slideButton2.setBackgroundResource(R.drawable.btnblue);
            slideButton1.setBackgroundResource(R.drawable.btnblue);
            slideButton4.setBackgroundResource(R.drawable.btnblue);
        }
        else if (sudah[position]==4){
            slideButton4.setBackgroundResource(R.drawable.btngreen);
            slideButton2.setBackgroundResource(R.drawable.btnblue);
            slideButton3.setBackgroundResource(R.drawable.btnblue);
            slideButton1.setBackgroundResource(R.drawable.btnblue);
        }


        slideButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sudah[position]=1;

                slideButton1.setBackgroundResource(R.drawable.btngreen);
                slideButton2.setBackgroundResource(R.drawable.btnblue);
                slideButton3.setBackgroundResource(R.drawable.btnblue);
                slideButton4.setBackgroundResource(R.drawable.btnblue);

                jawab[position] = slideButton1.getText().toString();
                    /*if (slideButton1.getText().toString().equals(library.getAnswer(position))) {
                        // Toast.makeText(MainActivity.this, "Benar", Toast.LENGTH_SHORT).show();
                        correct++;


                    } else {
                        wrong++;
                    }*/

            }
        });

        slideButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sudah[position]=2;

                slideButton2.setBackgroundResource(R.drawable.btngreen);
                slideButton1.setBackgroundResource(R.drawable.btnblue);
                slideButton3.setBackgroundResource(R.drawable.btnblue);
                slideButton4.setBackgroundResource(R.drawable.btnblue);

                jawab[position] = slideButton2.getText().toString();
                     /*   if (slideButton2.getText().toString().equals(library.getAnswer(position))) {
                            // Toast.makeText(MainActivity.this, "Benar", Toast.LENGTH_SHORT).show();
                            correct++;


                        } else {
                            wrong++;
                        }
                        */
            }
        });


        slideButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sudah[position]=3;
                slideButton3.setBackgroundResource(R.drawable.btngreen);
                slideButton2.setBackgroundResource(R.drawable.btnblue);
                slideButton1.setBackgroundResource(R.drawable.btnblue);
                slideButton4.setBackgroundResource(R.drawable.btnblue);


                jawab[position] = slideButton3.getText().toString();

                        /*if (slideButton3.getText().toString().equals(library.getAnswer(position))) {
                            // Toast.makeText(MainActivity.this, "Benar", Toast.LENGTH_SHORT).show();

                            correct++;

                        } else {
                            wrong++;
                        }
                        */

            }
        });

        slideButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sudah[position]=4;
                slideButton4.setBackgroundResource(R.drawable.btngreen);
                slideButton2.setBackgroundResource(R.drawable.btnblue);
                slideButton3.setBackgroundResource(R.drawable.btnblue);
                slideButton1.setBackgroundResource(R.drawable.btnblue);

                jawab[position] = slideButton4.getText().toString();


                        /*if (slideButton4.getText().toString().equals(library.getAnswer(position))) {
                            // Toast.makeText(MainActivity.this, "Benar", Toast.LENGTH_SHORT).show();
                            correct++;

                        } else {
                            wrong++;
                        }*/

            }
        });


        //}


        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
