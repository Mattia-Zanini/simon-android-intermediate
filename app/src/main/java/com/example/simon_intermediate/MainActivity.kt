package com.example.simon_intermediate

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.simon_intermediate.ui.theme.SimonIntermediateTheme

class MainActivity : ComponentActivity() {

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display on API level < 35
        enableEdgeToEdge()

        // Set and display the UI content
        setContent {
            SimonIntermediateTheme {

                // Reference: https://developer.android.com/develop/ui/compose/components/scaffold
                // The scaffold fills the whole display area
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // MainScreen consumes the insets to keep the app UI away from the system UI and display cutouts
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    MainScreen(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier)
{
    val orientation = LocalConfiguration.current.orientation

    // Reference: https://developer.android.com/develop/ui/compose/state-saving
    var c1 by rememberSaveable { mutableStateOf(false) }

    // Reference: https://developer.android.com/develop/ui/compose/layouts/constraintlayout
    ConstraintLayout(modifier = modifier) {
        val (sw1, tv, sw2) = createRefs()

        /*
        Switch(
            checked = c1,
            onCheckedChange = { c1 = it },
            modifier = Modifier.constrainAs(sw1) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    end.linkTo(tv.start)
                    bottom.linkTo(parent.bottom)
                }
                else {
                    end.linkTo(parent.end)
                    bottom.linkTo(tv.top)
                }
            }
        )
        */

        Button(
            modifier = Modifier.constrainAs(sw1) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                } else {
                }
            },
            onClick = {},
            shape = RoundedCornerShape(4.dp)
        ) {}

        /*Text(
            text = if (orientation == Configuration.ORIENTATION_LANDSCAPE)
                stringResource(R.string.land)
            else
                stringResource(R.string.port),
            modifier = Modifier.constrainAs(tv) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )*/
    }
}

// Reference: https://developer.android.com/develop/ui/compose/tooling/previews
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}