package com.example.easyquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    private CardView cardMath;
    private CardView cardIt;
    private CardView cardPhys;
    private CardView cardRus;
    private CardView cardHist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ImageButton button = findViewById(R.id.imageButtonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardMath = findViewById(R.id.cardMath);
        cardIt = findViewById(R.id.cardIt);
        cardPhys = findViewById(R.id.cardPhys);
        cardRus = findViewById(R.id.cardRus);
        cardHist = findViewById(R.id.cardHist);

        cardMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClassActivity("math");
            }
        });

        cardIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClassActivity("it");
            }
        });

        cardPhys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClassActivity("phys");
            }
        });

        cardRus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClassActivity("rus");
            }
        });

        cardHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClassActivity("hist");
            }
        });
    }

    public void startClassActivity(String subject) {
        Intent intent = new Intent(OptionActivity.this, ClassActivity.class);
        intent.putExtra("subject", subject);
        startActivity(intent);
    }
}


