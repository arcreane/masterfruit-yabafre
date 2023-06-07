package com.example.masterfruit_yabafre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.masterfruit_yabafre.ui.theme.MasterfruityabafreTheme
import kotlin.random.Random

class GuessANumber : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterfruityabafreTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    GuessANumberGame()
                }
            }
        }
    }
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessANumberGame(modifier: Modifier = Modifier) {
    var difficulty by remember { mutableStateOf(Difficulty.EASY) }
    var guess by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }
    var maxAttempts by remember { mutableStateOf(10) }
    var progress by remember { mutableStateOf(0f) }
    var score by remember { mutableStateOf(0) }
    val attemptsList = remember { mutableStateListOf<String>() }
    var randomNum by remember { mutableStateOf(0) }
    var gameStarted by remember { mutableStateOf(false) }
    var changeDifficulty by remember { mutableStateOf(false) }

    when (difficulty) {
        Difficulty.EASY -> randomNum = Random.nextInt(1, 11)
        Difficulty.MEDIUM -> randomNum = Random.nextInt(1, 51)
        Difficulty.HARD -> randomNum = Random.nextInt(1, 101)
    }

    Column(modifier = modifier.padding(16.dp)) {
        Row{
            Text(text = "Difficulté: $difficulty")
        }
        if (!gameStarted) {
            Row {
                Difficulty.values().forEach { diff ->
                    RadioButton(
                        selected = difficulty == diff,
                        onClick = { difficulty = diff; gameStarted = true }
                    )
                    Text(text = diff.name)
                }
            }
        }

        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Entrer un nombre") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val userGuess = guess.toIntOrNull()
            if (userGuess != null && attempts < maxAttempts) {
                attempts += 1
                progress = attempts.toFloat() / maxAttempts
                if (userGuess == randomNum) {
                    score = maxAttempts - attempts
                    attemptsList.add("$userGuess - Bravo!")
                } else if (userGuess < randomNum) {
                    attemptsList.add("$userGuess - Trop petit")
                } else {
                    attemptsList.add("$userGuess - Trop grand")
                }
                guess = ""
            }
        }, Modifier.padding(top = 16.dp)) {
            Text("Valider")
        }

        LinearProgressIndicator(progress = progress, Modifier.padding(top = 16.dp))

        attemptsList.forEach { attempt ->
            Text(attempt, Modifier.padding(top = 16.dp))
        }

        Text("Score: $score", Modifier.padding(top = 16.dp))

        if (attempts >= maxAttempts) {
            Row(Modifier.padding(8.dp)) {
                Checkbox(
                    checked = changeDifficulty,
                    onCheckedChange = { changeDifficulty = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Changer la difficulté")
            }
            if (changeDifficulty) {
                Difficulty.values().forEach { diff ->
                    RadioButton(
                        selected = difficulty == diff,
                        onClick = { difficulty = diff; gameStarted = false; changeDifficulty = false; attempts = 0 }
                    )
                    Text(text = diff.name, Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GuessANumberGamePreview() {
    MasterfruityabafreTheme {
        GuessANumberGame()
    }
}
