package com.sedate.qrku.feature.history.data

import android.util.Log
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.utils.extract
import com.sedate.qrku.core.common.utils.formatBarcode
import com.sedate.qrku.core.common.utils.formatDate
import com.sedate.qrku.core.common.utils.formatPlayStore
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.model.IconKey

object HistoryMapper {
    data class HistoryDisplay(
        val title: String, val subtitle: String, val icon: IconKey
    )

    fun map(history: BarcodeHistory): HistoryDisplay {
        return when (history.contentType) {
            "Contact" -> {
                val name = extract(history.result, "FN") ?: extract(history.result, "N")

                val phone = extract(history.result, "TEL")
                val email = extract(history.result, "EMAIL")

                HistoryDisplay(
                    title = name ?: "Contact",
                    subtitle = listOfNotNull(phone, email).joinToString(" • "),
                    icon = IconKey.CONTACT
                )
            }

            "Sms" -> {
                val phone = extract(history.result, "TEL")
                val body = extract(history.result, "BODY")

                HistoryDisplay(
                    title = phone ?: "SMS",
                    subtitle = body ?: "No message",
                    icon = IconKey.MESSAGE
                )
            }

            "Phone" -> {
                val number = history.result.removePrefix("tel:")

                HistoryDisplay(
                    title = number, subtitle = "Phone Number", icon = IconKey.PHONE
                )
            }

            "Email" -> {
                val email = history.result.substringAfter("mailto:").substringBefore("?")

                HistoryDisplay(
                    title = email, subtitle = "Email", icon = IconKey.EMAIL
                )
            }

            "Url" -> {
                val url = history.result

                HistoryDisplay(
                    title = url.removePrefix("https://").removePrefix("http://"),
                    subtitle = url,
                    icon = IconKey.LINK
                )
            }

            "Wifi" -> {
                val ssid = extract(history.result, "S")

                HistoryDisplay(
                    title = ssid ?: "WiFi", subtitle = "WiFi Network", icon = IconKey.WIFI
                )
            }

            "Calendar" -> {
                val title = Regex("SUMMARY:(.*)").find(history.result)?.groupValues?.get(1)

                val start = Regex("DTSTART:(.*)").find(history.result)?.groupValues?.get(1)

                HistoryDisplay(
                    title = title ?: "Event",
                    subtitle = start?.let { formatDate(it) } ?: String.EMPTY,
                    icon = IconKey.CALENDAR)
            }

            "Virtual Card" -> {
                val name = extract(history.result, "FN")
                val phone = extract(history.result, "TEL")
                val email = extract(history.result, "EMAIL")

                HistoryDisplay(
                    title = name ?: "Contact",
                    subtitle = listOfNotNull(phone, email).joinToString(" • "),
                    icon = IconKey.VCARD
                )
            }

            "Play Store" -> {
                HistoryDisplay(
                    title = formatPlayStore(history.result),
                    subtitle = String.EMPTY,
                    icon = IconKey.PLAY_STORE
                )
            }

            "Product" -> {
                val code = history.result

                HistoryDisplay(
                    title = formatBarcode(code), subtitle = String.EMPTY, icon = IconKey.BARCODE
                )
            }

            else -> {
                HistoryDisplay(
                    title = history.result, subtitle = String.EMPTY, icon = IconKey.TEXT
                )
            }
        }
    }
}