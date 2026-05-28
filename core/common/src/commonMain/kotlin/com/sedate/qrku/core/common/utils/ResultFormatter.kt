package com.sedate.qrku.core.common.utils

fun formatContact(raw: String): String {
    val name = extract(raw, "FN")
        ?: extract(raw, "N")

    val note = extract(raw, "NOTE")
        ?.let { "\nNote: $it" }

    return joinOrFallback(
        name,
        extract(raw, "TEL"),
        extract(raw, "EMAIL"),
        extract(raw, "ORG"),
        extract(raw, "ADR"),
        note,
        fallback = "Contact"
    )
}

fun formatWifi(raw: String): String {
    val ssid = extract(raw, "S") ?: "WiFi"
    val security = extract(raw, "T")

    return buildString {
        append(ssid)
        security?.takeIf { it.isNotEmpty() }?.let {
            append(" ($it)")
        }
    }
}

fun formatCalendar(raw: String): String {
    val title = extract(raw, "SUMMARY")
    val location = extract(raw, "LOCATION")
    val start = extract(raw, "DTSTART")

    val date = start?.let(::formatDate)

    return joinOrFallback(
        title,
        location,
        date,
        fallback = "Event"
    )
}

fun formatPhone(raw: String): String {
    return raw.removePrefix("tel:")
        .ifBlank { "Phone" }
}

fun formatEmail(raw: String): String {
    val email = raw.substringAfter("mailto:", "")
        .substringBefore("?")

    val subject = raw.substringAfter("subject=", "")
        .substringBefore("&")

    return joinOrFallback(
        email,
        subject.takeIf { it.isNotEmpty() },
        fallback = "Email"
    )
}

fun formatSms(raw: String): String {
    val parts = raw.removePrefix("SMSTO:")
        .split(":")

    val number = parts.getOrNull(0)
    val message = parts.getOrNull(1)

    return joinOrFallback(
        number,
        message,
        fallback = "SMS"
    )
}

fun formatPlayStore(raw: String): String {
    val packageName = raw.substringAfter("id=", "")

    return packageName
        .takeIf { it.isNotEmpty() }
        ?.let { "App • $it" }
        ?: "Play Store"
}

fun formatVCard(raw: String): String {
    return joinOrFallback(
        extract(raw, "FN"),
        extract(raw, "TEL"),
        extract(raw, "EMAIL"),
        extract(raw, "ORG"),
        extract(raw, "ADR"),
        fallback = "Contact"
    )
}

fun formatDate(raw: String): String {
    return try {
        val year = raw.substring(0, 4)
        val month = raw.substring(4, 6)
        val day = raw.substring(6, 8)
        val hour = raw.substring(9, 11)
        val minute = raw.substring(11, 13)

        "$day/$month/$year $hour:$minute"
    } catch (e: Exception) {
        raw
    }
}