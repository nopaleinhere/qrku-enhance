package com.sedate.qrku.feature.scan.ui.components

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(
	modifier: Modifier = Modifier,
	onPreviewReady: (PreviewView) -> Unit
) {
	AndroidView(
		modifier = modifier,
		factory = {
			PreviewView(it).also(onPreviewReady)
		}
	)
}