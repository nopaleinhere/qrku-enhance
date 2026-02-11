package com.sedate.qrku.core.ui.material.appbar

import com.sedate.qrku.core.common.constants.SeConst.EMPTY

data class AppBarState(
	val title: String = String.EMPTY,
	val type: AppBarType = AppBarType.NONE
)