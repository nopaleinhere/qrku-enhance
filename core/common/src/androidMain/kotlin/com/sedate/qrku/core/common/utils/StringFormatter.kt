package com.sedate.qrku.core.common.utils

import android.util.Patterns
import androidx.core.net.toUri

fun String.isValidUrl(): Boolean {
	return startsWith("http://", true) ||
			startsWith("https://", true)
}

fun String.isUrl(): Boolean {
	return Patterns.WEB_URL.matcher(this).matches()
}

fun normalizeUrl(url: String): String {
	return if (url.startsWith("http://")
			.not() && url.startsWith("https://")
			.not()
	) {
		"https://$url"
	} else url
}

fun extractDomain(url: String): String {
	return url.toUri().host ?: url
}