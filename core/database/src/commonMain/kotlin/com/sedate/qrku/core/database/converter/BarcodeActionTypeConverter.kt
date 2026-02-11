package com.sedate.qrku.core.database.converter

import androidx.room.TypeConverter
import com.sedate.qrku.core.common.constants.BarcodeType

class BarcodeActionTypeConverter {
	@TypeConverter
	fun fromActionType(value: BarcodeType.Action): String =
		value.name

	@TypeConverter
	fun toActionType(value: String): BarcodeType.Action =
		BarcodeType.Action.valueOf(value)
}