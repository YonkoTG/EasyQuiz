package com.example.easyquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CardView StartQuiz, History, About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        History.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HistActivity.class));
        });
        About.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        });
        StartQuiz.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, OptionActivity.class));

        });
    }

    private void initView() {
        StartQuiz = findViewById(R.id.StartQuiz);
        History = findViewById(R.id.HistoryQuiz);
        About = findViewById(R.id.AboutQuiz);
    }
}