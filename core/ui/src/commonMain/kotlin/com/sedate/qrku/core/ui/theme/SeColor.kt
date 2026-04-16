package com.sedate.qrku.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val LightPrimary = Color(0xFF0066CC)
private val LightOnPrimary = Color(0xFFFFFFFF)
private val LightPrimaryContainer = Color(0xFFD6E4FF)
private val LightOnPrimaryContainer = Color(0xFF001D36)

private val LightSecondary = Color(0xFF00A8A8)
private val LightOnSecondary = Color(0xFFFFFFFF)
private val LightSecondaryContainer = Color(0xFFB2EBF2)
private val LightOnSecondaryContainer = Color(0xFF002022)

private val LightTertiary = Color(0xFF4FC3F7)
private val LightOnTertiary = Color(0xFF00344F)

private val LightError = Color(0xFFB3261E)
private val LightOnError = Color(0xFFFFFFFF)
private val LightErrorContainer = Color(0xFFF9DEDC)
private val LightOnErrorContainer = Color(0xFF410E0B)

private val LightBackground = Color(0xFFF9FCFF)
private val LightOnBackground = Color(0xFF0F172A)

private val LightSurface = Color(0xFFFFFFFF)
private val LightOnSurface = Color(0xFF0F172A)

private val LightSurfaceVariant = Color(0xFFE3F2FD)
private val LightOnSurfaceVariant = Color(0xFF334155)

private val LightOutline = Color(0xFF94A3B8)
private val LightOutlineVariant = Color(0xFFCBD5E1)

// Dark theme colors
private val DarkPrimary = Color(0xFF90CAF9)
private val DarkOnPrimary = Color(0xFF002F6C)
private val DarkPrimaryContainer = Color(0xFF0A3D91)
private val DarkOnPrimaryContainer = Color(0xFFD6E4FF)

private val DarkSecondary = Color(0xFF4DD0E1)
private val DarkOnSecondary = Color(0xFF00363A)
private val DarkSecondaryContainer = Color(0xFF004F54)
private val DarkOnSecondaryContainer = Color(0xFFB2EBF2)

private val DarkTertiary = Color(0xFF81D4FA)
private val DarkOnTertiary = Color(0xFF00344F)

private val DarkError = Color(0xFFF2B8B5)
private val DarkOnError = Color(0xFF601410)
private val DarkErrorContainer = Color(0xFF8C1D18)
private val DarkOnErrorContainer = Color(0xFFF9DEDC)

private val DarkBackground = Color(0xFF0B1220)
private val DarkOnBackground = Color(0xFFE5EDFF)

private val DarkSurface = Color(0xFF0F172A)
private val DarkOnSurface = Color(0xFFE5EDFF)

private val DarkSurfaceVariant = Color(0xFF1E293B)
private val DarkOnSurfaceVariant = Color(0xFFCBD5E1)

private val DarkOutline = Color(0xFF64748B)
private val DarkOutlineVariant = Color(0xFF334155)

// Other
val Grey100 = Color(0xFFF5F5F5)
val Grey600 = Color(0xFF757575)
val Grey800 = Color(0xFF424242)

val LightColorScheme = lightColorScheme(
	primary = LightPrimary,
	onPrimary = LightOnPrimary,
	primaryContainer = LightPrimaryContainer,
	onPrimaryContainer = LightOnPrimaryContainer,
	secondary = LightSecondary,
	onSecondary = LightOnSecondary,
	secondaryContainer = LightSecondaryContainer,
	onSecondaryContainer = LightOnSecondaryContainer,
	tertiary = LightTertiary,
	onTertiary = LightOnTertiary,
	error = LightError,
	onError = LightOnError,
	errorContainer = LightErrorContainer,
	onErrorContainer = LightOnErrorContainer,
	background = LightBackground,
	onBackground = LightOnBackground,
	surface = LightSurface,
	onSurface = LightOnSurface,
	surfaceVariant = LightSurfaceVariant,
	onSurfaceVariant = LightOnSurfaceVariant,
	outline = LightOutline,
	outlineVariant = LightOutlineVariant
)

val DarkColorScheme = darkColorScheme(
	primary = DarkPrimary,
	onPrimary = DarkOnPrimary,
	primaryContainer = DarkPrimaryContainer,
	onPrimaryContainer = DarkOnPrimaryContainer,
	secondary = DarkSecondary,
	onSecondary = DarkOnSecondary,
	secondaryContainer = DarkSecondaryContainer,
	onSecondaryContainer = DarkOnSecondaryContainer,
	tertiary = DarkTertiary,
	onTertiary = DarkOnTertiary,
	error = DarkError,
	onError = DarkOnError,
	errorContainer = DarkErrorContainer,
	onErrorContainer = DarkOnErrorContainer,
	background = DarkBackground,
	onBackground = DarkOnBackground,
	surface = DarkSurface,
	onSurface = DarkOnSurface,
	surfaceVariant = DarkSurfaceVariant,
	onSurfaceVariant = DarkOnSurfaceVariant,
	outline = DarkOutline,
	outlineVariant = DarkOutlineVariant
)