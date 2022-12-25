package com.tlz.quizzarc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

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
    int questionNumber = 1;

    String userAnswer;

    int userCorrect = 0;
    int userWrong = 0;

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

        game();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "a";

                if (correctAnswer.equals(userAnswer)) {
                    a.setBackgroundColor(Color.GREEN);
                    userCorrect++;
                    correct.setText("" + userCorrect);
                } else {
                    a.setBackgroundColor(Color.RED);
                    userWrong++;
                    wrong.setText("" + userWrong);
                    findAnswer();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "b";

                if (correctAnswer.equals(userAnswer)) {
                    b.setBackgroundColor(Color.GREEN);
                    userCorrect++;
                    correct.setText("" + userCorrect);
                } else {
                    b.setBackgroundColor(Color.RED);
                    userWrong++;
                    wrong.setText("" + userWrong);
                    findAnswer();
                }
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "c";

                if (correctAnswer.equals(userAnswer)) {
                    c.setBackgroundColor(Color.GREEN);
                    userCorrect++;
                    correct.setText("" + userCorrect);
                } else {
                    c.setBackgroundColor(Color.RED);
                    userWrong++;
                    wrong.setText("" + userWrong);
                    findAnswer();
                }
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = "d";

                if (correctAnswer.equals(userAnswer)) {
                    d.setBackgroundColor(Color.GREEN);
                    userCorrect++;
                    correct.setText("" + userCorrect);
                } else {
                    d.setBackgroundColor(Color.RED);
                    userWrong++;
                    wrong.setText("" + userWrong);
                    findAnswer();
                }
            }
        });


    }

    public void game() {
        
        a.setBackgroundColor(Color.WHITE);
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
        d.setBackgroundColor(Color.WHITE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                
                questionCount = (int) dataSnapshot.getChildrenCount();
                
                quizQuestion = dataSnapshot.child(String.valueOf(questionNumber)).child("q").getValue().toString();
                answerA = dataSnapshot.child(String.valueOf(questionNumber)).child("a").getValue().toString();
                answerB = dataSnapshot.child(String.valueOf(questionNumber)).child("b").getValue().toString();
                answerC = dataSnapshot.child(String.valueOf(questionNumber)).child("c").getValue().toString();
                answerD = dataSnapshot.child(String.valueOf(questionNumber)).child("d").getValue().toString();
                correctAnswer = dataSnapshot.child(String.valueOf(questionNumber)).child("answer").getValue().toString();

                question.setText(quizQuestion);
                a.setText(answerA);
                b.setText(answerB);
                c.setText(answerC);
                d.setText(answerD);
                
                if (questionNumber < questionCount) {
                    questionNumber++;
                } else {
                    Toast.makeText(Quiz_Page.this, "You have completed the quiz", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(Quiz_Page.this, "An error has occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findAnswer() {
        switch (correctAnswer) {
            case "a":
                a.setBackgroundColor(Color.GREEN);
                break;
            case "b":
                b.setBackgroundColor(Color.GREEN);
                break;
            case "c":
                c.setBackgroundColor(Color.GREEN);
                break;
            case "d":
                d.setBackgroundColor(Color.GREEN);
                break;
        }
    }
}