package com.sedate.qrku.core.common.utils

import android.util.Patterns

fun String.isValidUrl(): Boolean {
	return startsWith("http://", true) ||
			startsWith("https://", true)
}

fun String.isUrl(): Boolean {
	return Patterns.WEB_URL.matcher(this).matches()
}