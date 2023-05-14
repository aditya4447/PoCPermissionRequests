package com.example.pocpermissionrequests.ui.feature.permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PermissionRationale(
    permission: String,
    explanation: String,
    onAllow: () -> Unit,
    onDismiss: () -> Unit,
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(explanation, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = onDismiss) {
                    Text("Cancel".uppercase())
                }
                TextButton(onClick = onAllow) {
                    Text("Allow".uppercase())
                }
            }
        }
    }
}
