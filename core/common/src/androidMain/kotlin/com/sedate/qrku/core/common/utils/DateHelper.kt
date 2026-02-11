package com.sedate.qrku.core.common.utils

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

fun getCurrentDateTimeFormatted(epochMillis: Long?): String {
	val formatter =
		DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

	val instant = if (epochMillis != null) {
		Instant.ofEpochMilli(epochMillis)
	} else {
		Instant.now()
	}

	val zonedDateTime = instant.atZone(ZoneId.systemDefault())

	return zonedDateTime.format(formatter)
}

fun formatDateHeader(epochMillis: Long): String {
	val formatter =
		DateTimeFormatter.ofPattern("dd MMMM yyyy")

	val instant = Instant.ofEpochMilli(epochMillis)

	val zonedDate = instant
		.atZone(ZoneId.systemDefault())
		.toLocalDate()

	return zonedDate.format(formatter)
}