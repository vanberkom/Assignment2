package com.example.assignment2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme


class MainActivity : ComponentActivity() {

    private val CUSTOM_PERM = "com.example.assignment2.MSE412"
    private val PERM_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (checkSelfPermission(CUSTOM_PERM) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(CUSTOM_PERM), PERM_CODE)
        }

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
                    },
                    onViewImageClick = {
                        val intent = Intent(this, ViewImageActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit, onViewImageClick: () -> Unit) {
    androidx.compose.foundation.layout.Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(text = "Name: Luke Van Berkom")
        Text(text = "ID: 1284201")

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(20.dp))

        Button(onClick = onExplicitClick) {
            Text(text = "Start Activity Explicitly")
        }

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))

        Button(onClick = onImplicitClick) {
            Text(text = "Start Activity Implicitly")
        }

        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))

        Button(onClick = onViewImageClick) {
            Text(text = "View Image Activity")
        }
    }
}