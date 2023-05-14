package com.example.pocpermissionrequests.ui.feature.permission

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocpermissionrequests.data.repository.PermissionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository
): ViewModel() {

    private val _permissionUiState = MutableStateFlow(PermissionUiState())
    val permissionUiState = _permissionUiState.asStateFlow()

    init {
        listenForLocationPermission()
        listenForCameraPermission()
    }

    private fun listenForLocationPermission() {
        permissionRepository
            .getPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .onEach { _permissionUiState.update { state -> state.copy(
                locationPermissionGranted = it,
                locationPermissionError = null
            )  } }
            .catch { _permissionUiState.update { state -> state.copy(
                locationPermissionGranted = false,
                locationPermissionError = it.localizedMessage ?: "Unknown error"
            ) } }
            .launchIn(viewModelScope)
    }

    private fun listenForCameraPermission() {
        permissionRepository
            .getPermission(Manifest.permission.CAMERA)
            .onEach { _permissionUiState.update { state -> state.copy(
                cameraPermissionGranted = it,
                cameraPermissionError = null
            )  } }
            .catch { _permissionUiState.update { state -> state.copy(
                cameraPermissionGranted = false,
                cameraPermissionError = it.localizedMessage ?: "Unknown error"
            ) } }
            .launchIn(viewModelScope)
    }

    fun showRationale(permission: String) {
        _permissionUiState.update { it.copy(rationalePermission = permission) }
    }

    fun dismissRationale() {
        _permissionUiState.update { it.copy(rationalePermission = null) }
    }
}
