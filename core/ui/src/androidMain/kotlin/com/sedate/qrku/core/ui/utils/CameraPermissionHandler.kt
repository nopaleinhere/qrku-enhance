package com.sedate.qrku.core.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun rememberCameraPermissionState(): CameraPermissionState {
	val context = LocalContext.current
	val activity = context as Activity

	var granted by remember {
		mutableStateOf(
			ContextCompat.checkSelfPermission(
				context,
				Manifest.permission.CAMERA
			) == PackageManager.PERMISSION_GRANTED
		)
	}

	var permanentlyDenied by remember { mutableStateOf(false) }

	val launcher = rememberLauncherForActivityResult(
		ActivityResultContracts.RequestPermission()
	) { isGranted ->
		granted = isGranted
		permanentlyDenied =
			isGranted.not() && ActivityCompat.shouldShowRequestPermissionRationale(
				activity,
				Manifest.permission.CAMERA
			)
				.not()
	}

	return remember {
		CameraPermissionState(
			isGranted = { granted },
			isPermanentlyDenied = { permanentlyDenied },
			request = { launcher.launch(Manifest.permission.CAMERA) },
			openSettings = { openAppSettings(context) }
		)
	}
}

class CameraPermissionState(
	val isGranted: () -> Boolean,
	val isPermanentlyDenied: () -> Boolean,
	val request: () -> Unit,
	val openSettings: () -> Unit
)

private fun openAppSettings(context: Context) {
	val intent = Intent(
		Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
		Uri.fromParts(
			"package",
			context.packageName,
			null
		)
	)
	context.startActivity(intent)
}