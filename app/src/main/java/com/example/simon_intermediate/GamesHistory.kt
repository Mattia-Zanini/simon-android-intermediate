package com.example.simon_intermediate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simon_intermediate.ui.theme.SimonIntermediateTheme

// Tag per il logger di debug di GamesHistory
const val tagHistoryD = "GamesHistory"

class GamesHistory : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(tagHistoryD, "onCreate Activity 2")

        enableEdgeToEdge()

        // Recupero la lista (se è null, creo una lista vuota)
        val historyData = intent.getStringArrayListExtra("GAMES_HISTORY") ?: arrayListOf<String>()

        setContent {
            SimonIntermediateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenTwo(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        historyList = historyData, // Qui passo historyData al composable della Schermata 2
                        buttonAction = {
                            this.finish() // Fa il popBack dello stack per tornare all'activity precedente
                        }
                    )
                }
            }
        }
    }
}

@Composable
// Dichiaro historyList come una List<String> in quanto deve SOLO leggere l'ArrayList<String> e NON modificarla
fun ScreenTwo(modifier: Modifier = Modifier, historyList: List<String>, buttonAction: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Margine esterno
    ) {
        // Titolo della schermata
        Row(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.history_title_text),
                modifier = modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        // Lista scorrevole delle cards delle partite precedenti
        repeat(historyList.size) { i ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                // Conteggio dei pulsanti cliccati
                Text(
                    text = historyList[i].split(", ").count().toString()
                )

                Text(
                    text = historyList[i],
                    maxLines = 1, // Forza il testo su una sola riga
                    overflow = TextOverflow.Ellipsis, // Aggiunge i "..." se il testo non ci sta
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = buttonAction) { Text(stringResource(R.string.back_btn)) }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    SimonIntermediateTheme {
        ScreenTwo(
            historyList = arrayListOf<String>(),
            buttonAction = {})
    }
}