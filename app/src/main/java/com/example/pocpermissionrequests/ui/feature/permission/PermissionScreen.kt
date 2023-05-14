package com.example.pocpermissionrequests.ui.feature.permission

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pocpermissionrequests.LocalActivity

@Composable
fun PermissionScreen(viewModel: PermissionViewModel = viewModel()) {
    val state by viewModel.permissionUiState.collectAsStateWithLifecycle()
    val activity = LocalActivity.current
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return@rememberLauncherForActivityResult
        }
        if (activity.shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION
            )) {
            viewModel.showRationale(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return@rememberLauncherForActivityResult
        }
        if (activity.shouldShowRequestPermissionRationale(
                Manifest.permission.CAMERA
            )) {
            viewModel.showRationale(Manifest.permission.CAMERA)
        }
    }
    Column(Modifier.fillMaxSize()) {
        Manifest.permission.ACCESS_FINE_LOCATION.let {
            PermissionItem(
                permissionName = it,
                state.locationPermissionGranted,
            ) {
                locationPermissionLauncher.launch(it)
            }
        }
        Spacer(
            Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onBackground))
        Manifest.permission.CAMERA.let {
            PermissionItem(
                permissionName = it,
                state.cameraPermissionGranted,
            ) {
                cameraPermissionLauncher.launch(it)
            }
        }
    }
    state.rationalePermission?.let {
        PermissionRationale(
            permission = it,
            explanation = when (it) {
                Manifest.permission.ACCESS_FINE_LOCATION -> {
                    "Explanation about why we need location permission"
                }
                Manifest.permission.CAMERA -> {
                    "Explanation about why we need camera permission"
                }
                else -> {
                    "We need this permission to provide you with features of the app."
                }
            },
            onAllow = {
                viewModel.dismissRationale()
                locationPermissionLauncher.launch(it)
            },
            onDismiss = {
                viewModel.dismissRationale()
            }
        )
    }
}

@Composable
fun PermissionItem(
    permissionName: String,
    permissionGranted: Boolean,
    onRequestPermission: () -> Unit,
) {


    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Row {
            Text(permissionName, modifier = Modifier.weight(1f))
            Box(modifier = Modifier.padding(start = 8.dp)) {
                if (permissionGranted) {
                    Text(text = "GRANTED", color = Color.Green)
                } else {
                    Button(onClick = onRequestPermission) {
                        Text(text = "Grant")
                    }
                }
            }
        }
    }
}
