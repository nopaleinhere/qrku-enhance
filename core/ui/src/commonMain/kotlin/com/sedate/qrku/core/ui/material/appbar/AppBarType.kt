package com.sedate.qrku.core.ui.material.appbar

import androidx.compose.ui.text.TextStyle
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.ui.theme.SeTextStyle

enum class AppBarType(
	val type: Int,
	val style: TextStyle? = null
) {
	NONE(
		Int.ZERO,
		null
	),
	TOP_LEVEL(
		Int.ONE,
		SeTextStyle.H3
	),
	SUB_LEVEL(
		Int.TWO,
		SeTextStyle.H4
	)
}