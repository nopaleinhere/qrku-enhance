package com.sedate.qrku.core.common.utils

import androidx.core.net.toUri

fun extractDomain(url: String): String {
    return url.toUri().host ?: url
}