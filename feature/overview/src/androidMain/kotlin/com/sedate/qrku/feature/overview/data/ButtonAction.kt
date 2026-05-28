package com.sedate.qrku.feature.overview.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonAction(
	val title: String,
	val icon: ImageVector,
	val color: Color,
	val onClick: () -> Unit
)