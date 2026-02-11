package com.sedate.qrku.core.model

import androidx.compose.runtime.Stable
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.ZERO

@Stable
data class BarcodeHistory(
	val id: Long = Long.ZERO,
	val actionType: BarcodeType.Action,
	val result: String,
	val contentType: String,
	val format: String,
	val formatCode: Int?,
	val createdAt: Long
)