package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.BarcodeFormat
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY

private fun Map<String, Any>.string(key: String, alt: String = String.EMPTY): String {
    return this[key] as? String ?: alt
}

private fun Map<String, Any>.bool(key: String): Boolean {
    return this[key] as? Boolean ?: false
}

fun mapToContent(
    type: BarcodeType.Support,
    values: Map<String, Any>
): GenerateContent {
    return when (type) {
        BarcodeType.Support.CONTACT ->
            GenerateContent.Contact(
                name = values.string("name"),
                phone = values.string("phone"),
                organization = values.string("organization"),
                address = values.string("address"),
                notes = values.string("notes")
            )

        BarcodeType.Support.PHONE ->
            GenerateContent.Phone(
                number = values.string("phone")
            )

        BarcodeType.Support.EMAIL ->
            GenerateContent.Email(
                email = values.string("email"),
                subject = values.string("subject"),
                body = values.string("body")
            )

        BarcodeType.Support.MESSAGE ->
            GenerateContent.Sms(
                number = values.string("phone"),
                message = values.string("message")
            )

        BarcodeType.Support.TEXT ->
            GenerateContent.Text(
                text = values.string("text")
            )

        BarcodeType.Support.LINK ->
            GenerateContent.Link(
                url = values.string("protocol", alt = "https://") + values.string("url")
            )

        BarcodeType.Support.WIFI ->
            GenerateContent.Wifi(
                ssid = values.string("ssid"),
                password = values.string("password"),
                security = values.string("security")
            )

        BarcodeType.Support.CALENDAR ->
            GenerateContent.Calendar(
                title = values.string("title"),
                location = values.string("location"),
                start = values.string("start"),
                end = values.string("end"),
                description = values.string("description")
            )

        BarcodeType.Support.VCARD ->
            GenerateContent.VCard(
                name = values.string("name"),
                phone = values.string("phone"),
                email = values.string("email"),
                company = values.string("company"),
                address = values.string("address")
            )

        BarcodeType.Support.PLAY_STORE ->
            GenerateContent.PlayStore(
                packageName = values.string("package")
            )

        BarcodeType.Support.UPC_A ->
            GenerateContent.Barcode(
                value = values.string("barcode"),
                format = BarcodeFormat.FORMAT_UPC_A
            )

        else ->
            GenerateContent.Text(values.string("value"))
    }
}