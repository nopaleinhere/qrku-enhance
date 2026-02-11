package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun ScannerCornerOverlay(
	boxScope: BoxScope,
	modifier: Modifier = Modifier,
	size: Dp = SeDimen.Dp320,
	cornerLength: Dp = SeDimen.Dp28,
	strokeWidth: Dp = SeDimen.Dp3,
	color: Color = colorScheme.secondaryContainer,
) = with(boxScope) {
	val corners = listOf(
		CornerConfig(
			Alignment.TopStart,
			top = true,
			start = true
		),
		CornerConfig(
			Alignment.TopEnd,
			top = true,
			start = false
		),
		CornerConfig(
			Alignment.BottomStart,
			top = false,
			start = true
		),
		CornerConfig(
			Alignment.BottomEnd,
			top = false,
			start = false
		),
	)

	Box(
		modifier = modifier
			.size(size)
			.align(Alignment.Center)
	) {
		corners.forEach { corner ->
			CornerCanvas(
				modifier = Modifier.align(corner.alignment),
				top = corner.top,
				start = corner.start,
				cornerLength = cornerLength,
				strokeWidth = strokeWidth,
				color = color
			)
		}
	}
}

@Composable
private fun CornerCanvas(
	modifier: Modifier,
	top: Boolean,
	start: Boolean,
	cornerLength: Dp,
	strokeWidth: Dp,
	color: Color
) {
	Canvas(modifier = modifier.size(cornerLength)) {
		val length = size.minDimension
		val stroke = strokeWidth.toPx()

		val origin = Offset(
			x = if (start) Float.ZERO else size.width,
			y = if (top) Float.ZERO else size.height
		)

		val horizontalEnd = origin + Offset(
			x = if (start) length else -length,
			y = Float.ZERO
		)

		val verticalEnd = origin + Offset(
			x = Float.ZERO,
			y = if (top) length else -length
		)

		drawLine(
			color = color,
			start = origin,
			end = horizontalEnd,
			strokeWidth = stroke,
			cap = StrokeCap.Square
		)

		drawLine(
			color = color,
			start = origin,
			end = verticalEnd,
			strokeWidth = stroke,
			cap = StrokeCap.Square
		)
	}
}

private data class CornerConfig(
	val alignment: Alignment,
	val top: Boolean,
	val start: Boolean
)


