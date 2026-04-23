package com.sedate.qrku.core.common.utils

fun formatContact(raw: String): String {
    val name = extract(raw, "N")
    val phone = extract(raw, "TEL")
    val email = extract(raw, "EMAIL")

    return listOfNotNull(name, phone, email)
        .joinToString(" • ")
        .ifEmpty { "Contact" }
}

fun formatPhone(raw: String): String {
    return raw.removePrefix("tel:")
}

fun formatEmail(raw: String): String {
    val email = raw.substringAfter("mailto:").substringBefore("?")
    val subject = raw.substringAfter("subject=", "").substringBefore("&")

    return if (subject.isNotEmpty())
        "$email • $subject"
    else email
}

fun formatSms(raw: String): String {
    val parts = raw.removePrefix("SMSTO:").split(":")

    val number = parts.getOrNull(0)
    val message = parts.getOrNull(1)

    return listOfNotNull(number, message)
        .joinToString(" • ")
}

fun formatWifi(raw: String): String {
    val ssid = extract(raw, "S")
    val type = extract(raw, "T")

    return listOfNotNull(ssid, type)
        .joinToString(" • ")
        .ifEmpty { "WiFi Network" }
}

fun formatCalendar(raw: String): String {
    val title = Regex("SUMMARY:(.*)").find(raw)?.groupValues?.get(1)
    val start = Regex("DTSTART:(.*)").find(raw)?.groupValues?.get(1)

    val formattedDate = start?.let { formatDate(it) }

    return listOfNotNull(title, formattedDate)
        .joinToString(" • ")
        .ifEmpty { "Event" }
}

fun formatVCard(raw: String): String {
    val name = extract(raw, "N")
    val phone = extract(raw, "TEL")
    val email = extract(raw, "EMAIL")

    return listOfNotNull(name, phone, email)
        .joinToString(" • ")
        .ifEmpty { "Contact" }
}

fun formatPlayStore(raw: String): String {
    val packageName = raw.substringAfter("id=", "")

    return if (packageName.isNotEmpty())
        "App • $packageName"
    else raw
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