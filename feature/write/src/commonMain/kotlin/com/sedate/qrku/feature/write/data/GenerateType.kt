package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.model.IconKey
import org.jetbrains.compose.resources.StringResource

data class GenerateType(
	val category: BarcodeType.Category,
	val support: BarcodeType.Support,
	val iconKey: IconKey
)