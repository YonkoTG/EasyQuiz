package com.example.easyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HistActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);

        ImageButton button = findViewById(R.id.imageButtonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);

        int totalTests = sharedPreferences.getInt("TotalTests", 0);
        int averagePercentage = sharedPreferences.getInt("AveragePercentage", 0);
        int totalaveragePercentage = sharedPreferences.getInt("TotalAveragePercentage", 0);
        String lastTest = sharedPreferences.getString("LastTest", "");

        TextView totalTestsTextView = findViewById(R.id.total_tests_textview);
        totalTestsTextView.setText("Всего пройдено тестов: " + totalTests);

        TextView totalaveragePercentageTextView = findViewById(R.id.total_average_percentage_textview);
        totalaveragePercentageTextView.setText("Средний процент пройденных тестов: " + totalaveragePercentage + "%");

        TextView lastTestTextView = findViewById(R.id.last_test_textview);
        lastTestTextView.setText("Последний тест: " + lastTest);

        TextView averagePercentageTextView = findViewById(R.id.average_percentage_textview);
        averagePercentageTextView.setText("Средний процент последнего теста: " + averagePercentage + "%");

    }
}