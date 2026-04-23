package com.sedate.qrku.feature.history.data

import com.sedate.qrku.core.common.utils.extract
import com.sedate.qrku.core.common.utils.formatDate
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.model.IconKey

object HistoryMapper {
	data class HistoryDisplay(
		val title: String,
		val subtitle: String,
		val icon: IconKey
	)

	fun map(history: BarcodeHistory): HistoryDisplay {
		return when (history.contentType) {
			"Contact" -> {
				val name = extract(history.result, "N")
				val phone = extract(history.result, "TEL")
				val email = extract(history.result, "EMAIL")

				HistoryDisplay(
					title = name ?: "Contact",
					subtitle = listOfNotNull(phone, email).joinToString(" • "),
					icon = IconKey.CONTACT
				)
			}

			"Phone" -> {
				val number = history.result.removePrefix("tel:")

				HistoryDisplay(
					title = number,
					subtitle = "Phone Number",
					icon = IconKey.PHONE
				)
			}

			"Email" -> {
				val email = history.result
					.substringAfter("mailto:")
					.substringBefore("?")

				HistoryDisplay(
					title = email,
					subtitle = "Email",
					icon = IconKey.EMAIL
				)
			}

			"Link" -> {
				val url = history.result

				HistoryDisplay(
					title = url.removePrefix("https://")
						.removePrefix("http://"),
					subtitle = url,
					icon = IconKey.LINK
				)
			}

			"Wifi" -> {
				val ssid = extract(history.result, "S")

				HistoryDisplay(
					title = ssid ?: "WiFi",
					subtitle = "WiFi Network",
					icon = IconKey.WIFI
				)
			}

			"Calendar" -> {
				val title = Regex("SUMMARY:(.*)")
					.find(history.result)?.groupValues?.get(1)

				val start = Regex("DTSTART:(.*)")
					.find(history.result)?.groupValues?.get(1)

				HistoryDisplay(
					title = title ?: "Event",
					subtitle = start?.let { formatDate(it) } ?: "",
					icon = IconKey.CALENDAR
				)
			}

			else -> {
				HistoryDisplay(
					title = history.result,
					subtitle = history.contentType,
					icon = IconKey.TEXT
				)
			}
		}
	}
}