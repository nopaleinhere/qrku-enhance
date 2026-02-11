package com.sedate.qrku

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform