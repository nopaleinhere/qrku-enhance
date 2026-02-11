package com.sedate.qrku.core.model

enum class SeTheme(val value: String) {
	LIGHT("light"),
	DARK("dark"),
	SYSTEM("system");

	companion object {
		fun getSeThemeByValue(themeValue: String): SeTheme {
			return entries.firstOrNull { it.value == themeValue } ?: SYSTEM
		}
	}
}