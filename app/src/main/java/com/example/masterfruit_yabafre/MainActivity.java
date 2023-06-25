package com.example.masterfruit_yabafre;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGuessANumber;
    private Button btnWordScrambler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuessANumber = findViewById(R.id.btnGuessANumber);
        btnWordScrambler = findViewById(R.id.btnWordScrambler);

        btnGuessANumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuessANumber.class));
            }
        });

        btnWordScrambler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WordScrambler.class));
            }
        });
    }
}
