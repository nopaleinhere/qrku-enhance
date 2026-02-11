package com.sedate.qrku.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.sedate.qrku.core.model.SeTheme
import com.sedate.qrku.core.ui.theme.SeTheme

@Composable
fun Theme(
	selectedTheme: String,
	content: @Composable () -> Unit
) {
	val currentTheme = SeTheme.getSeThemeByValue(selectedTheme)
	val darkTheme = when (currentTheme) {
		SeTheme.LIGHT -> false
		SeTheme.DARK -> true
		SeTheme.SYSTEM -> isSystemInDarkTheme()
	}

	SeTheme(darkTheme = darkTheme) {
		content()
	}
}