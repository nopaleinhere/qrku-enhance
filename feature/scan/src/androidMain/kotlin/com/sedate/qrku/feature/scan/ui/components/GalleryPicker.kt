package com.sedate.qrku.feature.scan.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun rememberGalleryPicker(
	onImagePicked: (Uri) -> Unit,
	onCancel: () -> Unit
): () -> Unit {

	val launcher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent()
	) { uri ->
		if (uri == null) {
			onCancel()
		} else {
			onImagePicked(uri)
		}
	}

	return {
		launcher.launch("image/*")
	}
}