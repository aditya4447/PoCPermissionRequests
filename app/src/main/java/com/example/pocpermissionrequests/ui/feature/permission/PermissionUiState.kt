package com.example.pocpermissionrequests.ui.feature.permission

data class PermissionUiState(
    val locationPermissionGranted: Boolean = false,
    val locationPermissionError: String? = null,
    val cameraPermissionGranted: Boolean = false,
    val cameraPermissionError: String? = null,
    val rationalePermission: String? = null,
)
