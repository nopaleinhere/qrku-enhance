package com.sedate.qrku.core.common.utils

import com.sedate.qrku.core.common.constants.SeConst.SPACE

fun StringBuilder.appendField(key: String, value: String) {
    if (value.isNotEmpty()) {
        append("$key:${escape(value)};")
    }
}

fun encodeUrl(value: String): String {
    return value.replace(
        String.SPACE,
        "%20"
    )
}

fun joinOrFallback(
    vararg values: String?,
    fallback: String
): String {
    return values
        .filterNot { it.isNullOrBlank() }
        .joinToString("\n")
        .ifEmpty { fallback }
}

fun normalizeUrl(url: String): String {
    return if (url.startsWith("http://")
            .not() && url.startsWith("https://")
            .not()
    ) {
        "https://$url"
    } else url
}

fun formatBarcode(code: String): String {
    return code.chunked(4).joinToString(" ")
}

fun extract(raw: String, key: String): String? {
    val pattern = when {
        raw.startsWith("MECARD:", ignoreCase = true) -> {
            Regex("""$key:([^;]*)""")
        }

        raw.contains("BEGIN:VCARD", ignoreCase = true) -> {
            Regex("""$key(?:;[^:]+)?:([^\n\r]*)""")
        }

        raw.startsWith("SMSTO:", ignoreCase = true) -> {
            when (key) {
                "TEL" -> Regex("""SMSTO:([^:]+)""")
                "BODY" -> Regex("""SMSTO:[^:]+:(.*)""")
                else -> Regex("""$key:([^;\n\r]*)""")
            }
        }

        raw.startsWith("MATMSG:", ignoreCase = true) -> {
            Regex("""$key:([^;]*)""")
        }

        raw.startsWith("WIFI:", ignoreCase = true) -> {
            Regex("""$key:([^;]*)""")
        }

        else -> {
            Regex("""$key:([^;\n\r]*)""")
        }
    }

    return pattern
        .find(raw)
        ?.groupValues
        ?.getOrNull(1)
        ?.trim()
        ?.replace("\\,", ",")
        ?.replace("\\n", "\n")
        ?.replace("\\;", ";")
        ?.replace("\\", "")
        ?.takeIf(String::isNotBlank)
}

fun escape(value: String): String {
    return value
        .replace("\\", "\\\\")
        .replace(";", "\\;")
        .replace(",", "\\,")
        .replace(":", "\\:")
}