package com.example.masterfruit_yabafre;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordScrambler extends AppCompatActivity {

    private TextView textViewScrambledWord;
    private EditText editTextGuess;
    private Button buttonGuess;
    private TextView textViewScore;
    private TextView textViewTime;
    private int score = 0;
    private String currentWord;
    private String[] words = {"apple", "banana", "cherry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_scrambler);

        textViewScrambledWord = findViewById(R.id.textViewScrambledWord);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonGuess = findViewById(R.id.buttonGuess);
        textViewScore = findViewById(R.id.textViewScore);
        textViewTime = findViewById(R.id.textViewTime);

        nextWord();

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = editTextGuess.getText().toString();
                if (guess.equalsIgnoreCase(currentWord)) {
                    score++;
                    Toast.makeText(WordScrambler.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WordScrambler.this, "Wrong!", Toast.LENGTH_SHORT).show();
                }
                textViewScore.setText("Score: " + score);
                editTextGuess.setText("");
                nextWord();
            }
        });

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTime.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                buttonGuess.setEnabled(false);
                textViewTime.setText("done!");
            }
        }.start();
    }

    private void nextWord() {
        Random random = new Random();
        currentWord = words[random.nextInt(words.length)];
        String scrambledWord = scramble(currentWord);
        textViewScrambledWord.setText(scrambledWord);
    }

    private String scramble(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String scrambled = "";
        for (String letter : letters) {
            scrambled += letter;
        }
        return scrambled;
    }
}
