package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.ZER0_POINT_SIX
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun ScannerDarkOverlay(
	modifier: Modifier = Modifier,
	scanSize: Dp = SeDimen.Dp320,
	overlayColor: Color = Color.Black.copy(alpha = Float.ZER0_POINT_SIX)
) {
	BoxWithConstraints(modifier.fillMaxSize()) {
		val w = (maxWidth - scanSize) / Int.TWO
		val h = (maxHeight - scanSize) / Int.TWO

		listOf(
			Modifier.fillMaxWidth()
				.height(h),
			Modifier.fillMaxWidth()
				.height(h)
				.align(Alignment.BottomStart),
			Modifier.width(w)
				.height(scanSize)
				.align(Alignment.CenterStart),
			Modifier.width(w)
				.height(scanSize)
				.align(Alignment.CenterEnd)
		).forEach {
			Box(it.background(overlayColor))
		}
	}
}