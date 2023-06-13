package com.example.easyquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.security.auth.Subject;

public class TestActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button buttonOption1;
    private Button buttonOption2;
    private Button buttonOption3;
    private Button buttonOption4;
    private TextView textViewQuestionCount;
    private TextView textViewSolvedCount;

    private List<Question> questionList;
    private int currentQuestionIndex;
    private int totalQuestionCount;
    private int solvedQuestionCount;

    public String textsub;
    public String textclass;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Инициализация компонентов пользовательского интерфейса
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        buttonOption4 = findViewById(R.id.buttonOption4);
        textViewQuestionCount = findViewById(R.id.textViewQuestionCount);
        textViewSolvedCount = findViewById(R.id.textViewSolvedCount);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String grade = intent.getStringExtra("grade");

        TextView textViewSub = findViewById(R.id.textViewSub);
        if(Objects.equals(subject, "math")){
            textsub = "Математика";
        } else if (Objects.equals(subject, "it")) {
            textsub = "Информатика";
        } else if (Objects.equals(subject, "phys")) {
            textsub = "Физика";
        } else if (Objects.equals(subject, "hist")) {
            textsub = "История";
        } else if (Objects.equals(subject, "rus")) {
            textsub ="Русский язык";
        }

        if(Objects.equals(grade, "grade7")){
            textclass = "7 класс";
        } else if (Objects.equals(grade, "grade8")) {
            textclass = "8 класс";
        } else if (Objects.equals(grade, "grade9")) {
            textclass = "9 класс";
        } else if (Objects.equals(grade, "grade10")) {
            textclass = "10 класс";
        } else if (Objects.equals(grade, "grade11")) {
            textclass ="11 класс";
        }

        textViewSub.setText(textsub + " " + textclass);

        // Получение списка вопросов
        QuestionBank questionBank = new QuestionBank(getApplicationContext(), subject, grade);
        questionList = questionBank.getQuestions(subject, grade);

        // Инициализация счетчиков
        currentQuestionIndex = 0;
        totalQuestionCount = Math.min(questionList.size(), 15); // Ограничение вопросов до 15
        solvedQuestionCount = 0;

        // Отображение вопроса
        displayQuestion();

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        buttonOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(4);
            }
        });
    }

    private void displayQuestion() {
        // Проверка, есть ли еще вопросы
        if (currentQuestionIndex < totalQuestionCount) {
            int randomIndex = (int) (Math.random() * totalQuestionCount);
            Question currentQuestion = questionList.get(randomIndex);

            textViewQuestion.setText(currentQuestion.getQuestion());
            buttonOption1.setText(currentQuestion.getOption1());
            buttonOption2.setText(currentQuestion.getOption2());
            buttonOption3.setText(currentQuestion.getOption3());
            buttonOption4.setText(currentQuestion.getOption4());

            textViewQuestionCount.setText(String.format(Locale.getDefault(), "%d/%d", currentQuestionIndex + 1, totalQuestionCount));
            textViewSolvedCount.setText(String.valueOf(solvedQuestionCount));
        } else {
            finishTest();
        }
    }

    private void checkAnswer(int selectedOption) {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        if (selectedOption == currentQuestion.getCorrectOption()) {
            //правильный ответ
            solvedQuestionCount++;
        }

        // Следующий вопрос
        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishTest() {
        // Обновление данных
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TotalTests", sharedPreferences.getInt("TotalTests", 0) + 1);
        int totalQuestions = questionList.size();
        editor.apply();

        // Сохранение среднего процента
        int averagePercentage = (int) ((float) solvedQuestionCount / totalQuestionCount * 100);
        editor.putInt("AveragePercentage", averagePercentage);
        editor.putString("LastTest", textsub + " " + textclass);
        editor.apply();

        int totalTests = sharedPreferences.getInt("TotalTests", 0);
        int totalaveragePercentage = sharedPreferences.getInt("TotalAveragePercentage", 0);
        int TotalAveragePercentage = ((totalaveragePercentage * totalTests) + averagePercentage) / (totalTests + 1);
        editor.putInt("TotalAveragePercentage", TotalAveragePercentage);
        editor.apply();

        // Завершение теста
        Toast.makeText(this, "Тест завершен", Toast.LENGTH_SHORT).show();

        // Результаты
        String resultMessage = String.format(Locale.getDefault(), "Всего вопросов: %d\nПравильно решено: %d", totalQuestionCount, solvedQuestionCount);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Результаты")
                .setMessage(resultMessage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Закрытие
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}