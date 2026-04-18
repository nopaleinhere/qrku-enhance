package com.sedate.qrku.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Other
val Grey100 = Color(0xFFF5F5F5)
val Grey600 = Color(0xFF757575)
val Grey800 = Color(0xFF424242)

val LightColorScheme = lightColorScheme(
	primary = Color(0xFF021A54),
	onPrimary = Color(0xFFFFFFFF),
	primaryContainer = Color(0xFF3A4D8F),
	onPrimaryContainer = Color(0xFFE3E8FF),

	secondary = Color(0xFFFF85BB),
	onSecondary = Color(0xFF4A0030),
	secondaryContainer = Color(0xFFFFCEE3),
	onSecondaryContainer = Color(0xFF3A0024),

	tertiary = Color(0xFFFFCEE3),
	onTertiary = Color(0xFF4A0030),
	tertiaryContainer = Color(0xFFFFE4F0),
	onTertiaryContainer = Color(0xFF3A0024),

	error = Color(0xFFBA1A1A),
	onError = Color(0xFFFFFFFF),
	errorContainer = Color(0xFFFFDAD6),
	onErrorContainer = Color(0xFF93000A),

	background = Color(0xFFF5F5F5),
	onBackground = Color(0xFF1A1A1A),

	surface = Color(0xFFF5F5F5),
	onSurface = Color(0xFF1A1A1A),

	surfaceVariant = Color(0xFFE1E3EB),
	onSurfaceVariant = Color(0xFF44474F),

	outline = Color(0xFF74777F),
	outlineVariant = Color(0xFFC4C6CF),
)

val DarkColorScheme = darkColorScheme()