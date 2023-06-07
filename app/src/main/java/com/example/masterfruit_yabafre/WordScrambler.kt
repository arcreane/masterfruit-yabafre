package com.example.masterfruit_yabafre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.masterfruit_yabafre.ui.theme.MasterfruityabafreTheme

class WordScrambler : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterfruityabafreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WordScramblerGame()
                }
            }
        }
    }
}


fun String.scramble(): String {
    return this.toList().shuffled().joinToString("")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordScramblerGame(modifier: Modifier = Modifier) {
    var difficulty by remember { mutableStateOf(Difficulty.EASY) }
    var score by remember { mutableStateOf(0) }
    var guess by remember { mutableStateOf("") }
    val wordList = listOf("apple", "banana", "cherry")
    var currentWord by remember { mutableStateOf(wordList.random()) }
    var scrambledWord by remember { mutableStateOf(currentWord.scramble()) }
    val attempts = remember { mutableStateListOf<String>() }

    Column(modifier = modifier) {
        Row {
            Difficulty.values().forEach { diff ->
                RadioButton(
                    selected = difficulty == diff,
                    onClick = { difficulty = diff }
                )
                Text(text = diff.name)
            }
        }

        Text(text = "Scrambled Word: $scrambledWord")

        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Enter your guess") }
        )

        Button(onClick = {
            val userGuess = guess
            if (userGuess.isNotEmpty()) {
                if (userGuess == currentWord) {
                    score += 1
                    currentWord = wordList.random()
                    scrambledWord = currentWord.scramble()
                    attempts.add("$userGuess - Correct!")
                } else {
                    attempts.add("$userGuess - Incorrect")
                }
                guess = ""
            }
        }, Modifier.padding(top = 16.dp)) {
            Text("Submit")
        }

        attempts.forEach { attempt ->
            Text(attempt)
        }

        Text("Score: $score", Modifier.padding(top = 16.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MasterfruityabafreTheme {
        WordScramblerGame()
    }
}
