package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme

class ChallengeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                ChallengesScreen(
                    onBackClick = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ChallengesScreen(onBackClick: () -> Unit) {
    val challenges = listOf(
        "1. Limited device resources depending on phone (battery, memory, CPU)",
        "2. Device Fragmentation",
        "3. Security and privacy concerns",
        "4. Oversaturation of the Play Store (>3.5 million apps)",
        "5. Ensuring good performance and UX"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(29.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Top Challenges of Mobile Software Engineering:\n")

        challenges.forEach { challenge ->
            Text(text = challenge, modifier = Modifier.padding(vertical = 5.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = onBackClick) {
            Text(text = "Main Activity")
        }
    }
}