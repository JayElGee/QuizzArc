package com.tlz.quizzarc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference databaseReferenceSecond = database.getReference();

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
    
    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 25000;
    boolean timerCont;
    long timeLeft = TOTAL_TIME;

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

                resetTimer();
                game();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendScore();
                Intent intent = new Intent(Quiz_Page.this, Score_Page.class);
                startActivity(intent);
                finish();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pauseTimer();

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

                pauseTimer();

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

                pauseTimer();

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

                pauseTimer();

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

        startTimer();

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

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerCont = false;
                pauseTimer();
                question.setText("Time has expired!");
            }
        }.start();

        timerCont = true;
    }

    public void resetTimer() {
        timeLeft = TOTAL_TIME;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int second = (int) (timeLeft / 1000) % 60;
        time.setText("" + second);
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        timerCont = false;
    }

    public void sendScore() {
        String userUID = user.getUid();
        databaseReferenceSecond.child("scores").child(userUID).child("correct").setValue(userCorrect)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Quiz_Page.this, "Scores submitted!", Toast.LENGTH_SHORT).show();
                            }
                        });
        databaseReferenceSecond.child("scores").child(userUID).child("incorrect").setValue(userWrong);
    }
}