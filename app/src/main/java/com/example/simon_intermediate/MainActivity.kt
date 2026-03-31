package com.example.simon_intermediate

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.simon_intermediate.ui.theme.SimonIntermediateTheme

class MainActivity : ComponentActivity() {

    // Viene chiamato quando l'activity viene creata per la prima volta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Abilita la visualizzazione "edge-to-edge" (a tutto schermo) per le versioni API < 35
        enableEdgeToEdge()

        // Imposta e visualizza il contenuto dell'interfaccia utente (UI)
        setContent {
            SimonIntermediateTheme {

                // Reference: https://developer.android.com/develop/ui/compose/components/scaffold
                // Lo scaffold riempie l'intera area del display
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // MainScreen utilizza gli "insets" (presenti in 'innerPadding') per mantenere
                    // l'interfaccia utente lontana dalla UI di sistema e dai ritagli del display (come il notch)
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val orientation = LocalConfiguration.current.orientation

    // Reference: https://developer.android.com/develop/ui/compose/state-saving
    var txt by rememberSaveable { mutableStateOf("") }

    // Reference: https://developer.android.com/develop/ui/compose/layouts/constraintlayout
    ConstraintLayout(modifier = modifier) {
        val (b1, b2, b3, b4, b5, b6, textBox, endGame, delete) = createRefs()

        // Shape uguale per tutti i pulsanti
        val btnShape = RoundedCornerShape(6.dp)
        // Lista contenete tutti i colori dei pulsanti
        val colors =
            listOf<Color>(
                Color.Red,
                Color.Green,
                Color.Blue,
                Color.Magenta,
                Color.Yellow,
                Color.Cyan
            )

        val btnStrings =
            listOf<String>(
                "R",
                "G",
                "B",
                "M",
                "Y",
                "C"
            )

        // Margine interno tra i bottoni
        val innerMargin = 6.dp
        // Margine esterno tra i bottoni
        val externalMargin = 16.dp

        // Lista di tutti i modifier dei pulsanti
        val btnModifiers = listOf<Modifier>(
            // B1
            Modifier.constrainAs(b1) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(parent.start, margin = externalMargin)
                    end.linkTo(b2.start, margin = innerMargin)
                    top.linkTo(parent.top, margin = externalMargin)
                    bottom.linkTo(b3.top, margin = innerMargin)
                }
            },

            //B2
            Modifier.constrainAs(b2) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(b1.end, margin = innerMargin)
                    end.linkTo(parent.end, margin = externalMargin)
                    top.linkTo(parent.top, margin = externalMargin)
                    bottom.linkTo(b4.top, margin = innerMargin)
                }
            },

            //B3
            Modifier.constrainAs(b3) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(parent.start, margin = externalMargin)
                    end.linkTo(b4.start, margin = innerMargin)
                    top.linkTo(b1.bottom, margin = innerMargin)
                    bottom.linkTo(b5.top, margin = innerMargin)
                }
            },

            //B4
            Modifier.constrainAs(b4) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(b3.end, margin = innerMargin)
                    end.linkTo(parent.end, margin = externalMargin)
                    top.linkTo(b2.bottom, margin = innerMargin)
                    bottom.linkTo(b6.top, margin = innerMargin)
                }
            },

            //B5
            Modifier.constrainAs(b5) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(parent.start, margin = externalMargin)
                    end.linkTo(b6.start, margin = innerMargin)
                    top.linkTo(b3.bottom, margin = innerMargin)
                    bottom.linkTo(textBox.top, margin = externalMargin)
                }
            },

            //B6
            Modifier.constrainAs(b6) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                    start.linkTo(b5.end, margin = innerMargin)
                    end.linkTo(parent.end, margin = externalMargin)
                    top.linkTo(b4.bottom, margin = innerMargin)
                    bottom.linkTo(textBox.top, margin = externalMargin)
                }
            }
        )

        // Ciclo per disegnare i pulsanti (usando i riferimenti creati sopra)
        repeat(6) { index ->
            Button(
                modifier = btnModifiers[index],
                shape = btnShape,
                colors = ButtonDefaults.filledTonalButtonColors(colors[index]),
                onClick = {
                    Log.d("MainActivity - Button", "B${index + 1}: ${btnStrings[index]} clicked")

                    if (txt == "")
                        txt += btnStrings[index]
                    else
                        txt += ", " + btnStrings[index]
                }
            ) {}
        }

        // Reference: https://developer.android.com/reference/kotlin/androidx/compose/foundation/rememberScrollState.composable
        // Crea e "ricorda" un oggetto che mantiene traccia della posizione attuale dello scorrimento.
        val scrollState = rememberScrollState()

        Text(
            text = txt,
            modifier = Modifier
                .constrainAs(textBox) {
                    width = Dimension.fillToConstraints
                    height = Dimension.value(200.dp)

                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)

                    top.linkTo(b5.bottom)
                    top.linkTo(b6.bottom)

                    bottom.linkTo(delete.bottom, margin = 8.dp)
                    bottom.linkTo(endGame.bottom, margin = 8.dp)
                }
                // Applica il colore allo sfondo della text
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                // Aggiungo il padding (Margine interno), questo sposta il testo lontano dai bordi del box
                .padding(16.dp)

                // Collega lo stato al modificatore per abilitare lo scroll
                .verticalScroll(scrollState),
        )

        // Cancella
        Button(
            modifier = Modifier.constrainAs(delete) {
                start.linkTo(parent.start, margin = 16.dp)
                top.linkTo(textBox.bottom)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            },
            onClick = {
                Log.d("MainActivity - Button", "'Delete' clicked")

                txt = ""
            }
        ) { Text(stringResource(R.string.delete_sequence_btn)) }

        // Fine Partita
        Button(
            modifier = Modifier.constrainAs(endGame) {
                end.linkTo(parent.end, margin = 16.dp)
                top.linkTo(textBox.bottom)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            },
            onClick = {
                Log.d("MainActivity - Button", "'End Game' clicked")
            }
        ) { Text(stringResource(R.string.end_game_btn)) }
    }
}

// Reference: https://developer.android.com/develop/ui/compose/tooling/previews
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}