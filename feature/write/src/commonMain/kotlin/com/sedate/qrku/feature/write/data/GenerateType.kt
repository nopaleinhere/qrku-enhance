package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.BarcodeType
import org.jetbrains.compose.resources.StringResource

data class GenerateType(
	val category: BarcodeType.Category,
	val support: BarcodeType.Support,
	val iconKey: IconKey
)

enum class IconKey {
	NONE,
	CONTACT,
	PHONE,
	EMAIL,
	MESSAGE,
	TEXT,
	LINK,
	WIFI,
	CALENDAR,
	VCARD,
	PLAY_STORE,
	BARCODE
}