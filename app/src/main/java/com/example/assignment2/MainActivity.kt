package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.assignment2.ui.theme.Assignment2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                MainScreen(
                    onExplicitClick = {
                        val intent = Intent(this, ChallengeActivity::class.java)
                        startActivity(intent)
                    },
                    onImplicitClick = {
                        val intent = Intent("com.example.assignment2.SHOW_CHALLENGES")
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit) {
    Text(
        text = "Name: Luke Van Berkom"
    )

    Text(
        text = "ID: 1284201"
    )

    Button(onClick = onExplicitClick) {
        Text(text = "Start Activity Explicitly")
    }

    Button(onClick = onImplicitClick) {
        Text(text = "Start Activity Implicitly")
    }
}