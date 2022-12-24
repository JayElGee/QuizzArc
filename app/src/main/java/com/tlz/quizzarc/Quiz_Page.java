package com.tlz.quizzarc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Quiz_Page extends AppCompatActivity {

    TextView time, correct, wrong;
    TextView question, a, b, c, d;
    Button next, end;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference().child("Questions");

    String quizQuestion;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String correctAnswer;
    int questionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        time = findViewById(R.id.tvTime);
        correct = findViewById(R.id.tvCorrect);
        wrong = findViewById(R.id.tvWrong);
        question = findViewById(R.id.tvViewQuestion);
        a = findViewById(R.id.tvA);
        b = findViewById(R.id.tvB);
        c = findViewById(R.id.tvC);
        d = findViewById(R.id.tvD);

        next = findViewById(R.id.buttonNextQuestion);
        end = findViewById(R.id.buttonEndGame);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}