package com.example.easyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class ClassActivity extends AppCompatActivity {

    private CardView cardSev;
    private CardView cardEit;
    private CardView cardNine;
    private CardView cardTen;
    private CardView cardElew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        ImageButton button = findViewById(R.id.imageButtonBack2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassActivity.this, OptionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        String selectedSubject = intent.getStringExtra("subject");

        cardSev = findViewById(R.id.cardSev);
        cardEit = findViewById(R.id.cardEit);
        cardNine = findViewById(R.id.cardNine);
        cardTen = findViewById(R.id.cardTen);
        cardElew = findViewById(R.id.cardElew);

        if (selectedSubject.equals("Математика")) {
            cardSev.setVisibility(View.VISIBLE);
            cardEit.setVisibility(View.VISIBLE);
            cardNine.setVisibility(View.VISIBLE);
            cardTen.setVisibility(View.VISIBLE);
            cardElew.setVisibility(View.VISIBLE);
        } else if (selectedSubject.equals("Информатика")) {
            cardSev.setVisibility(View.GONE);
            cardEit.setVisibility(View.VISIBLE);
            cardNine.setVisibility(View.VISIBLE);
            cardTen.setVisibility(View.VISIBLE);
            cardElew.setVisibility(View.VISIBLE);
        } else if (selectedSubject.equals("Физика")) {
            cardSev.setVisibility(View.VISIBLE);
            cardEit.setVisibility(View.VISIBLE);
            cardNine.setVisibility(View.VISIBLE);
            cardTen.setVisibility(View.VISIBLE);
            cardElew.setVisibility(View.VISIBLE);
        } else if (selectedSubject.equals("Русский язык")) {
            cardSev.setVisibility(View.VISIBLE);
            cardEit.setVisibility(View.VISIBLE);
            cardNine.setVisibility(View.VISIBLE);
            cardTen.setVisibility(View.VISIBLE);
            cardElew.setVisibility(View.VISIBLE);
        } else if (selectedSubject.equals("История")) {
            cardSev.setVisibility(View.VISIBLE);
            cardEit.setVisibility(View.VISIBLE);
            cardNine.setVisibility(View.VISIBLE);
            cardTen.setVisibility(View.VISIBLE);
            cardElew.setVisibility(View.VISIBLE);
        }

        cardSev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity("grade7", selectedSubject);
            }
        });

        cardEit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity("grade8", selectedSubject);
            }
        });

        cardNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity("grade9", selectedSubject);
            }
        });

        cardTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity("grade10", selectedSubject);
            }
        });

        cardElew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity("grade11", selectedSubject);
            }
        });
    }

    public void startTestActivity(String grade, String subject) {
        Intent intent = new Intent(ClassActivity.this, TestActivity.class);
        intent.putExtra("subject", subject);
        intent.putExtra("grade", grade);
        startActivity(intent);
    }
}