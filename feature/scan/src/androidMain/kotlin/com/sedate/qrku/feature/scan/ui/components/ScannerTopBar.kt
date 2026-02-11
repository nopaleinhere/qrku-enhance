package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun ScannerTopBar(
	modifier: Modifier = Modifier,
	isFlash: Boolean,
	onFlashClick: () -> Unit,
	onGalleryClick: () -> Unit
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.statusBarsPadding()
			.padding(
				horizontal = SeDimen.Dp16,
				vertical = SeDimen.Dp12
			),
		horizontalArrangement = Arrangement.End,
		verticalAlignment = Alignment.CenterVertically
	) {

		IconButton(
			onClick = onFlashClick
		) {
			Icon(
				imageVector = if (isFlash) {
					Icons.Filled.FlashOn
				} else {
					Icons.Filled.FlashOff
				},
				contentDescription = "FLASH_IC",
				tint = Color.White
			)
		}

		Spacer(modifier = Modifier.width(SeDimen.Dp8))

		IconButton(
			onClick = onGalleryClick
		) {
			Icon(
				imageVector = Icons.Filled.PhotoLibrary,
				contentDescription = "GALLERY_IC",
				tint = Color.White
			)
		}
	}
}
