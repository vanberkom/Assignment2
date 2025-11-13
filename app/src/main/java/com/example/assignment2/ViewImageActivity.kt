package com.example.assignment2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.assignment2.ui.theme.Assignment2Theme
import java.io.File

class ViewImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                ViewImageScreen()
            }
        }
    }
}

@Composable
fun ViewImageScreen() {
    val context = LocalContext.current

    // Persist URI across configuration changes
    var imageUri by rememberSaveable { mutableStateOf<String?>(null) }

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
    }


    var currentPhotoUri by remember { mutableStateOf<Uri?>(null) }


    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ComponentActivity.RESULT_OK) {

            imageUri = currentPhotoUri?.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                if (!hasCameraPermission) {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                } else {

                    val imageDir = File(context.cacheDir, "images").apply { mkdirs() }
                    val imageFile = File.createTempFile("captured_", ".jpg", imageDir)
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        imageFile
                    )

                    currentPhotoUri = uri

                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                        putExtra(MediaStore.EXTRA_OUTPUT, uri)
                        addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }

                    cameraLauncher.launch(intent)
                }
            }
        ) {
            Text(text = if (hasCameraPermission) "Capture Image" else "Grant Camera Permission")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(Uri.parse(imageUri)),
                contentDescription = "Captured image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(text = "No image captured yet.")
        }
    }
}
