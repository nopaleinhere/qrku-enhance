package com.sedate.qrku.core.ui.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.google.zxing.client.result.AddressBookParsedResult
import com.google.zxing.client.result.CalendarParsedResult
import com.google.zxing.client.result.EmailAddressParsedResult
import com.google.zxing.client.result.ParsedResult
import com.google.zxing.client.result.ProductParsedResult
import com.google.zxing.client.result.ResultParser
import com.google.zxing.client.result.SMSParsedResult
import com.google.zxing.client.result.TelParsedResult
import com.google.zxing.client.result.TextParsedResult
import com.google.zxing.client.result.URIParsedResult
import com.google.zxing.client.result.WifiParsedResult

fun parseScanResult(
    value: String,
    format: BarcodeFormat
): ParsedContent {
    val result = Result(
        value,
        null,
        null,
        format
    )
    val parsed = ResultParser.parseResult(result)

    return parsed.toParsedContent(value)
}


private fun ParsedResult.toParsedContent(raw: String): ParsedContent {
    return when (this) {
        is URIParsedResult -> ParsedContent.Url(uri)

        is TextParsedResult -> {
            if (raw.startsWith("http://") || raw.startsWith("https://")) {
                ParsedContent.Url(raw)
            } else {
                ParsedContent.Text(text)
            }
        }

        is TelParsedResult -> ParsedContent.Phone(
            number = number
        )

        is WifiParsedResult -> ParsedContent.Wifi(
            ssid = ssid,
            password = password,
            encryption = networkEncryption
        )

        is AddressBookParsedResult -> ParsedContent.Contact(
            name = names?.firstOrNull(),
            phone = phoneNumbers?.firstOrNull(),
            email = emails?.firstOrNull(),
            organization = org,
            address = addresses?.firstOrNull(),
            note = note
        )

        is EmailAddressParsedResult -> ParsedContent.Email(
            email = tos?.firstOrNull(),
            subject = subject,
            body = body
        )

        is SMSParsedResult -> ParsedContent.Sms(
            number = numbers?.firstOrNull(),
            message = body
        )

        is CalendarParsedResult -> ParsedContent.Calendar(
            title = summary,
            location = location,
            description = description,
            start = startTimestamp.toString(),
            end = endTimestamp.toString()
        )

        is ProductParsedResult -> ParsedContent.Product(
            code = productID
        )

        else -> ParsedContent.Text(displayResult.ifEmpty { raw })
    }
}

sealed class ParsedContent {
    data class Url(val url: String) : ParsedContent()
    data class Text(val text: String) : ParsedContent()
    data class Product(val code: String) : ParsedContent()

    data class Phone(val number: String) : ParsedContent()
    data class Wifi(
        val ssid: String,
        val password: String?,
        val encryption: String
    ) : ParsedContent()

    data class Contact(
        val name: String?,
        val phone: String?,
        val email: String?,
        val organization: String?,
        val address: String?,
        val note: String?
    ) : ParsedContent()

    data class Email(
        val email: String?,
        val subject: String?,
        val body: String?
    ) : ParsedContent()

    data class Sms(
        val number: String?,
        val message: String?
    ) : ParsedContent()

    data class Calendar(
        val title: String?,
        val location: String?,
        val description: String?,
        val start: String?,
        val end: String?
    ) : ParsedContent()

}
