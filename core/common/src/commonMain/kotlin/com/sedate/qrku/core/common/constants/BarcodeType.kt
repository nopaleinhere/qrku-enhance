package com.sedate.qrku.core.common.constants

import com.sedate.qrku.core.common.constants.BarcodeFormat.FORMAT_QR_CODE
import com.sedate.qrku.core.common.constants.BarcodeFormat.FORMAT_UNKNOWN
import com.sedate.qrku.core.common.constants.BarcodeFormat.FORMAT_UPC_A
import com.sedate.qrku.core.common.constants.BarcodeFormat.TYPE_UNKNOWN
import com.sedate.qrku.core.common.constants.BarcodeFormat.TYPE_URL
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.ZERO

object BarcodeType {
	enum class Support(
		val text: String,
		val contentType: String,
		val format: Int,
		val type: Int = TYPE_UNKNOWN,
		val maxLength: Int = Int.ZERO,
		val lineLimits: Int = Int.ONE
	) {
		TEXT(
			text = "Text",
			contentType = "Text",
			format = FORMAT_QR_CODE,
			lineLimits = Int.MAX_VALUE
		),
		LINK(
			text = "Url",
			contentType = "Url",
			format = FORMAT_QR_CODE,
			type = TYPE_URL,
		),
		UPC_A(
			text = "UPC_A",
			contentType = "Product",
			format = FORMAT_UPC_A,
			maxLength = 12
		),
		UNKNOWN(
			text = "Unknown",
			contentType = "Unknown",
			format = FORMAT_UNKNOWN
		);

		companion object {
			fun fromFormat(
				format: Int?
			): Support =
				entries.firstOrNull { it.format == format }
					?: UNKNOWN
		}
	}

	enum class Action {
		SCAN,
		CREATE,
		HISTORY
	}
}