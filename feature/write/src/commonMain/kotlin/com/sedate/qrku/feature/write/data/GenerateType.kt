package com.sedate.qrku.feature.write.data

import org.jetbrains.compose.resources.StringResource

data class GenerateType<T>(
	val type: String,
	val format: T,
	val desc: StringResource,
	val iconKey: IconKey = IconKey.NONE
)

enum class IconKey {
	NONE,
	TEXT,
	LINK,
	BARCODE
}