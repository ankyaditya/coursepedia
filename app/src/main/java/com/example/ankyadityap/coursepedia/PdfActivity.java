package com.example.ankyadityap.coursepedia;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity {

    PDFView pdfView;
    String pdfName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Bundle extras = getIntent().getExtras();
        pdfName = extras.getString("pdf");
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(pdfName)
                .load();
    }
}
