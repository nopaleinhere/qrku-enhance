package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun CameraPermissionContent(
	onRequest: () -> Unit,
	onOpenSettings: () -> Unit,
	permanentlyDenied: Boolean
) {
	val colors = ButtonColors(
		containerColor = colorScheme.secondaryContainer,
		contentColor = colorScheme.onPrimary,
		disabledContainerColor = colorScheme.onSurface,
		disabledContentColor = colorScheme.onPrimary
	)

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("Camera permission is required to scan QR code")

		Spacer(Modifier.height(SeDimen.Dp16))

		if (permanentlyDenied) {
			Button(
				colors = colors,
				onClick = onOpenSettings
			) {
				Text("Open Settings")
			}
		} else {
			Button(
				colors = colors,
				onClick = onRequest
			) {
				Text("Allow Camera")
			}
		}
	}
}