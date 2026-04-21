package com.sedate.qrku.core.common.constants

import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TWELVE
import com.sedate.qrku.core.common.constants.SeConst.ZERO

object BarcodeType {
	enum class Support(
		val text: String,
		val contentType: String,
		val format: Int,
		val type: Int = BarcodeFormat.TYPE_UNKNOWN,
		val maxLength: Int = Int.ZERO,
		val lineLimits: Int = Int.ONE
	) {
		CONTACT(
			text = "Contact",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_TEXT
		),
		PHONE(
			text = "Phone",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_PHONE,
		),
		EMAIL(
			text = "Email",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_EMAIL
		),
		MESSAGE(
			text = "SMS",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_SMS
		),
		TEXT(
			text = "Text",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_TEXT,
			maxLength = Int.MAX_VALUE
		),
		LINK(
			text = "Link",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_URL
		),
		WIFI(
			text = "Wifi",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_WIFI
		),
		CALENDAR(
			text = "Calendar",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_CALENDAR_EVENT
		),
		VCARD(
			text = "VCard",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_CONTACT_INFO
		),
		PLAY_STORE(
			text = "Play Store",
			contentType = "QR Code",
			format = BarcodeFormat.FORMAT_QR_CODE,
			type = BarcodeFormat.TYPE_URL
		),
		UPC_A(
			text = "UPC-A",
			contentType = "Barcode",
			format = BarcodeFormat.FORMAT_UPC_A,
			type = BarcodeFormat.TYPE_TEXT,
			maxLength = Int.TWELVE
		),
		UNKNOWN(
			text = "Unknown",
			contentType = "Unknown",
			format =BarcodeFormat. FORMAT_UNKNOWN
		);

		companion object {
			fun fromFormat(
				format: Int?
			): Support =
				entries.firstOrNull { it.format == format }
					?: UNKNOWN
		}
	}

	enum class Category {
		QR, BARCODE
	}

	enum class Action {
		SCAN,
		CREATE,
		HISTORY
	}
}