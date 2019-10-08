package com.example.android.braintest;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbu;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion=0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView sumtextView;
    TextView resultTextView;
    TextView pointsTextview;
    TextView timertextView;
    Button palyAgainButton;
    RelativeLayout gameRealtiveLayout;

    public void playAgain(View view) {
        gameRealtiveLayout.setBackgroundColor(Color.parseColor("#4A148C"));
        score=0;
        numberOfQuestion=0;
        timertextView.setText("30s");
        resultTextView.setText("");
        pointsTextview.setText("0/0");
        palyAgainButton.setVisibility(View.INVISIBLE);
        generatedQuestion();
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timertextView.setText(String.valueOf(millisUntilFinished/1000 + "s"));
            }
            @Override
            public void onFinish() {
                palyAgainButton.setVisibility( View.VISIBLE);
                timertextView.setText("0");
                resultTextView.setText("YOUR SCORE"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);



            }
        }.start();

    }
    public void generatedQuestion() {
        Random rand=new Random();

        int a=rand.nextInt(51);
        int b=rand.nextInt(31);

        sumtextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);
        int incorrecctAnswer;
        answers.clear();

        for(int i=0;i<4;i++) {
            if(i==locationOfCorrectAnswer) {
                answers.add(a+b);

            }
            else {
                incorrecctAnswer=rand.nextInt(82 );
                while (incorrecctAnswer==a+b) {
                    incorrecctAnswer=rand.nextInt(82 );
                }
                answers.add(incorrecctAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view) {

        startbu.setVisibility(View.INVISIBLE);
        ///startbu.animate().translationYBy(1000f).rotation(360).setDuration(300);
        gameRealtiveLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbu=(Button)findViewById(R.id.startButton);
        sumtextView=(TextView) findViewById(R.id.sumTextView);
        button1=(Button) findViewById(R.id.ansButton1);
        button2=(Button) findViewById(R.id.ansButton2);
        button3=(Button) findViewById(R.id.ansButton3);
        button4=(Button) findViewById(R.id.ansButton4);
        resultTextView=(TextView) findViewById(R.id.resultTextView);
        pointsTextview=(TextView) findViewById(R.id.pointTextView);
        timertextView=(TextView) findViewById(R.id.timerTextView);
        palyAgainButton=(Button) findViewById(R.id.playAgainButton);
        gameRealtiveLayout=(RelativeLayout) findViewById(R.id.gameRelatibeLauout);

    }


    public void chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("CORRECT!");
            gameRealtiveLayout.setBackgroundColor(Color.parseColor("#33691E"));
        }
        else
        {
            resultTextView.setText("WRONG!");
            gameRealtiveLayout.setBackgroundColor(Color.parseColor("#C62828"));

        }
        numberOfQuestion++;
        pointsTextview.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generatedQuestion();

    }




}
