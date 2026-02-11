package com.sedate.qrku.feature.overview.ui.view

import androidx.compose.runtime.Composable
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.feature.overview.viewmodel.QrCaptureViewModel

@Composable
actual fun QrCaptureView(
	navigator: Navigator,
	actionType: BarcodeType.Action,
	type: String,
	input: String,
	format: Int?,
	date: Long?
) {
	/* NO-OP */
}