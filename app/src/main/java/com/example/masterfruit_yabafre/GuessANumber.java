package com.example.masterfruit_yabafre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessANumber extends AppCompatActivity {

    private EditText editTextGuess;
    private Button buttonGuess;
    private TextView textViewResult;
    private RadioButton radioButtonEasy, radioButtonMedium, radioButtonHard;
    private ProgressBar progressBar;
    private int randomNum;
    private int guessCount;

    private static final int MAX_GUESS_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_a_number);

        editTextGuess = findViewById(R.id.editTextGuess);
        buttonGuess = findViewById(R.id.buttonGuess);
        textViewResult = findViewById(R.id.textViewResult);
        radioButtonEasy = findViewById(R.id.radioButtonEasy);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonHard = findViewById(R.id.radioButtonHard);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(MAX_GUESS_COUNT);

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessCount < MAX_GUESS_COUNT) {
                    int guess = Integer.parseInt(editTextGuess.getText().toString());
                    if (guess == randomNum) {
                        textViewResult.setText("Bravo!");
                    } else if (guess < randomNum) {
                        textViewResult.setText("Trop petit");
                    } else {
                        textViewResult.setText("Trop grand");
                    }
                    guessCount++;
                    progressBar.setProgress(guessCount);
                    editTextGuess.setText("");
                } else {
                    textViewResult.setText("Vous avez atteint le nombre maximal d'essais");
                    buttonGuess.setEnabled(false);
                }
            }
        });

        radioButtonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNum = new Random().nextInt(10) + 1;
            }
        });

        radioButtonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNum = new Random().nextInt(50) + 1;
            }
        });

        radioButtonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomNum = new Random().nextInt(100) + 1;
            }
        });
    }
}
