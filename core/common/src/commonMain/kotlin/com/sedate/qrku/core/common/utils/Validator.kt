package com.sedate.qrku.core.common.utils

fun isValidDateFormat(value: String): Boolean {
    if (value.isEmpty()) return true

    val regex = Regex("""\d{8}T\d{6}""")

    if (!regex.matches(value)) return false

    val year = value.substring(0, 4).toIntOrNull() ?: return false
    val month = value.substring(4, 6).toIntOrNull() ?: return false
    val day = value.substring(6, 8).toIntOrNull() ?: return false
    val hour = value.substring(9, 11).toIntOrNull() ?: return false
    val minute = value.substring(11, 13).toIntOrNull() ?: return false

    return month in 1..12 &&
            day in 1..31 &&
            hour in 0..23 &&
            minute in 0..59
}

fun String.isValidUrl(): Boolean {
    return startsWith("http://", true) ||
            startsWith("https://", true)
}


fun isValidUpcA(code: String): Boolean {
    if (code.length != 12 || code.any { !it.isDigit() }) return false

    val digits = code.map { it.digitToInt() }

    val oddSum = digits
        .filterIndexed { index, _ -> index % 2 == 0 }
        .sum()

    val evenSum = digits
        .filterIndexed { index, _ -> index % 2 == 1 }
        .sum()

    val total = (oddSum * 3) + evenSum

    return total % 10 == 0
}