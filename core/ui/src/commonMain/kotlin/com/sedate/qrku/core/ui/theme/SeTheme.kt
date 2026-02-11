package com.sedate.qrku.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SeTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colorScheme = if (darkTheme) {
		DarkColorScheme
	} else {
		LightColorScheme
	}

	MaterialTheme(
		typography = SeTypography,
		colorScheme = colorScheme,
		content = content
	)
}