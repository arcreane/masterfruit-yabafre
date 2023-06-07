package com.example.masterfruit_yabafre

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.masterfruit_yabafre.ui.theme.MasterfruityabafreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterfruityabafreTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, GuessANumber::class.java))
                        })
                        {
                            Text("Play Guess A Number")
                        }

                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, WordScrambler::class.java))
                        }, Modifier.padding(top = 16.dp)) {
                            Text("Play Word Scrambler")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MasterfruityabafreTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                }) {
                    Text("Play Guess A Number")
                }
                Button(onClick = {
                }, Modifier.padding(top = 16.dp)) {
                    Text("Play Word Scrambler")
                }
            }
        }
    }
}