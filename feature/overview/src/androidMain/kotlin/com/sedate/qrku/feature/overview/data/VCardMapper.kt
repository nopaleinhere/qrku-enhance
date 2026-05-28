package com.sedate.qrku.feature.overview.data

import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.utils.extract

fun String.toVCardData(): VCardData {
	return VCardData(
		name = extract(this, "FN")
			?: extract(this, "N")
			?: String.EMPTY,
		company = extract(this, "ORG").orEmpty(),
		position = extract(this, "TITLE").orEmpty(),
		numberPhone = extract(this, "TEL").orEmpty(),
		email = extract(this, "EMAIL").orEmpty(),
		address = extract(this, "ADR").orEmpty()
	)
}