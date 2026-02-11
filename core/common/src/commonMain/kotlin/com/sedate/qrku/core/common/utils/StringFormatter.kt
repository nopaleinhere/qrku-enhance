package com.sedate.qrku.core.common.utils

fun String.isValidUrl(): Boolean {
	return startsWith("http://", true) ||
			startsWith("https://", true)
}