package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.SeConst.SPACE
import com.sedate.qrku.core.common.utils.appendField
import com.sedate.qrku.core.common.utils.encodeUrl
import com.sedate.qrku.core.common.utils.escape

object CodeEncoder {

    fun encode(content: GenerateContent): String =
        when (content) {
            is GenerateContent.Contact -> encodeContact(content)
            is GenerateContent.Phone -> encodePhone(content)
            is GenerateContent.Email -> encodeEmail(content)
            is GenerateContent.Sms -> encodeSms(content)
            is GenerateContent.Text -> content.text
            is GenerateContent.Link -> content.url
            is GenerateContent.Wifi -> encodeWifi(content)
            is GenerateContent.Calendar -> encodeCalendar(content)
            is GenerateContent.VCard -> encodeVCard(content)
            is GenerateContent.PlayStore -> encodePlayStore(content)
            is GenerateContent.Barcode -> content.value
        }

    private fun encodeContact(content: GenerateContent.Contact): String =
        buildString {
            append("MECARD:")
            appendField("N", content.name)
            appendField("TEL", content.phone)
            appendField("EMAIL", content.email)
            appendField("ORG", content.organization)
            appendField("ADR", content.address)
            appendField("NOTE", content.notes)
            append(";")
        }
    private fun encodeWifi(content: GenerateContent.Wifi): String {
        val security = if (content.security == "None") "" else content.security

        return buildString {
            append("WIFI:")
            append("T:$security;")
            append("S:${escape(content.ssid)};")
            append("P:${escape(content.password)};")
            append(";")
        }
    }

    private fun encodeEmail(content: GenerateContent.Email): String =
        buildString {
            append("mailto:${content.email}")

            val params = buildList {
                if (content.subject.isNotEmpty()) {
                    add("subject=${encodeUrl(content.subject)}")
                }
                if (content.body.isNotEmpty()) {
                    add("body=${encodeUrl(content.body)}")
                }
            }

            if (params.isNotEmpty()) {
                append("?${params.joinToString("&")}")
            }
        }

    private fun encodeCalendar(content: GenerateContent.Calendar): String =
        buildString {
            appendLine("BEGIN:VEVENT")
            appendLine("SUMMARY:${escape(content.title)}")
            appendLine("LOCATION:${escape(content.location)}")
            appendLine("DESCRIPTION:${escape(content.description)}")
            appendLine("DTSTART:${content.start}")
            appendLine("DTEND:${content.end}")
            appendLine("END:VEVENT")
        }

    private fun encodePhone(content: GenerateContent.Phone) =
        "tel:${content.number}"

    private fun encodeSms(content: GenerateContent.Sms) =
        "SMSTO:${content.number}:${escape(content.message)}"

    private fun encodePlayStore(content: GenerateContent.PlayStore) =
        "https://play.google.com/store/apps/details?id=${content.packageName}"

    private fun encodeVCard(content: GenerateContent.VCard): String =
        buildString {
            appendLine("BEGIN:VCARD")
            appendLine("VERSION:3.0")
            appendLine("FN:${escape(content.name)}")
            appendLine("N:${escape(content.name)}")
            appendLine("TEL:${escape(content.phone)}")
            appendLine("EMAIL:${escape(content.email)}")
            appendLine("ORG:${escape(content.company)}")
            appendLine("ADR:${escape(content.address)}")
            appendLine("END:VCARD")
        }
}