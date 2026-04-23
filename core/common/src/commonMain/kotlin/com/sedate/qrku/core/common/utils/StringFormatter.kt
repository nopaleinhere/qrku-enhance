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
    return Regex("$key:(.*?);").find(raw)?.groupValues?.get(1)
}

fun escape(value: String): String {
    return value
        .replace("\\", "\\\\")
        .replace(";", "\\;")
        .replace(",", "\\,")
        .replace(":", "\\:")
}