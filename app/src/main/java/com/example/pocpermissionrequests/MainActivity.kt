package com.example.pocpermissionrequests

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.example.pocpermissionrequests.ui.feature.permission.PermissionScreen
import com.example.pocpermissionrequests.ui.theme.PoCPermissionRequestsTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalActivity = staticCompositionLocalOf<ComponentActivity> {
    error("CompositionLocal LocalActivity not present")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalActivity provides this) {
                PoCPermissionRequestsTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        PermissionScreen()
                    }
                }
            }
        }
    }
}
