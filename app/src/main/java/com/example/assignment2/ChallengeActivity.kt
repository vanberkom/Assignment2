package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.assignment2.ui.theme.Assignment2Theme

class ChallengeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {

            }
        }
    }
}

@Composable
fun ChallengesScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Top Challenges of Mobile Software Dev:\n",
        modifier = modifier
    )
}