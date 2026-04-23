package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.SeConst.EMPTY

sealed class GenerateContent {
    data class Contact(
        val name: String,
        val phone: String,
        val email: String = String.EMPTY,
        val organization: String = String.EMPTY,
        val address: String = String.EMPTY,
        val notes: String = String.EMPTY
    ) : GenerateContent()

    data class Phone(val number: String) : GenerateContent()
    data class Email(
        val email: String,
        val subject: String = String.EMPTY,
        val body: String = String.EMPTY
    ) : GenerateContent()

    data class Sms(
        val number: String,
        val message: String
    ) : GenerateContent()

    data class Text(val text: String) : GenerateContent()

    data class Link(val url: String) : GenerateContent()

    data class Wifi(
        val ssid: String,
        val password: String,
        val security: String
    ) : GenerateContent()

    data class Calendar(
        val title: String,
        val location: String,
        val start: String,
        val end: String,
        val description: String
    ) : GenerateContent()

    data class VCard(
        val name: String,
        val phone: String,
        val email: String,
        val company: String,
        val address: String
    ) : GenerateContent()

    data class PlayStore(val packageName: String) : GenerateContent()

    data class Barcode(
        val value: String,
        val format: Int
    ) : GenerateContent()
}
