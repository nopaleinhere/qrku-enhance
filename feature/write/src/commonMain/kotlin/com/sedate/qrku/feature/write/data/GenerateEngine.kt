package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.BarcodeFormat
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.SPACE
import com.sedate.qrku.feature.write.viewmodel.FormState

class GenerateEngine {
    fun execute(content: GenerateContent): Pair<String, Int> {
        val encoded = CodeEncoder.encode(content)

        val format = when (content) {
            is GenerateContent.Barcode -> content.format
            else -> BarcodeFormat.FORMAT_QR_CODE
        }

        return encoded to format
    }
}

object CodeEncoder {
    fun encode(content: GenerateContent): String {
        return when (content) {
            is GenerateContent.Contact -> buildString {
                append("MECARD:")
                append("N:${content.name};")
                append("TEL:${content.phone};")

                if (content.email.isNotEmpty()) {
                    append("EMAIL:${content.email};")
                }

                append(";")
            }

            is GenerateContent.Phone ->
                "tel:${content.number}"

            is GenerateContent.Email ->
                "mailto:${content.email}" +
                        "?subject=${encodeUrl(content.subject)}" +
                        "&body=${encodeUrl(content.body)}"

            is GenerateContent.Sms ->
                "SMSTO:${content.number}:${content.message}"

            is GenerateContent.Text ->
                content.text

            is GenerateContent.Link ->
                content.url

            is GenerateContent.Wifi -> {
                val security = if (content.security == "None") String.EMPTY else content.security

                "WIFI:T:$security;S:${content.ssid};P:${content.password};;"
            }

            is GenerateContent.Calendar ->
                """
                BEGIN:VEVENT
                SUMMARY:${content.title}
                LOCATION:${content.location}
                DTSTART:${content.start}
                DTEND:${content.end}
                END:VEVENT
                """.trimIndent()

            is GenerateContent.VCard ->
                """
                BEGIN:VCARD
                VERSION:3.0
                N:${content.name}
                TEL:${content.phone}
                EMAIL:${content.email}
                ORG:${content.company}
                ADR:${content.address}
                END:VCARD
                """.trimIndent()

            is GenerateContent.PlayStore ->
                "https://play.google.com/store/apps/details?id=${content.packageName}"

            is GenerateContent.Barcode ->
                content.value
        }
    }

    private fun encodeUrl(value: String): String {
        return value.replace(
            String.SPACE,
            "%20"
        )
    }
}

fun validateForm(
    fields: List<FormPart>,
    state: FormState
): Boolean {
    var isValid = true

    fields.forEach { field ->
        val error = field.validate(state.values[field.key])
        state.errors[field.key] = error
        if (error != null) isValid = false
    }

    return isValid
}