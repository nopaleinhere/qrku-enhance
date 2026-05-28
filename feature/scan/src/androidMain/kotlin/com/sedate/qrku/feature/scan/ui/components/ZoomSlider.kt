package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.common.constants.SeConst.FIFTY
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_ONE
import com.sedate.qrku.core.ui.utils.SeDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZoomSlider(
	value: Float,
	onValueChange: (Float) -> Unit,
	valueRange: ClosedFloatingPointRange<Float>,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = SeDimen.Dp32),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			imageVector = Icons.Default.Remove,
			contentDescription = "zoom_out",
			tint = colorScheme.surface,
			modifier = Modifier
				.size(SeDimen.Dp24)
				.clickable {
					val newValue = (value - Float.ZERO_POINT_ONE).coerceIn(
						valueRange.start,
						valueRange.endInclusive
					)
					onValueChange(newValue)
				}
		)

		Slider(
			value = value,
			onValueChange = onValueChange,
			valueRange = valueRange,
			modifier = Modifier
				.weight(1f)
				.padding(horizontal = SeDimen.Dp6),
			thumb = {
				Box(
					modifier = Modifier
						.size(SeDimen.Dp18)
						.background(
							colorScheme.secondary,
							CircleShape
						)
				)
			},
			track = { sliderPositions ->
				SliderDefaults.Track(
					sliderState = sliderPositions,
					colors = SliderDefaults.colors(
						activeTrackColor = colorScheme.onSecondary,
						inactiveTrackColor = colorScheme.outline
					),
					modifier = Modifier.height(SeDimen.Dp6)
						.clip(RoundedCornerShape(Int.FIFTY))
				)
			}
		)

		Icon(
			imageVector = Icons.Default.Add,
			contentDescription = "zoom_in",
			tint = colorScheme.surface,
			modifier = Modifier
				.size(SeDimen.Dp24)
				.clickable {
					val newValue = (value + Float.ZERO_POINT_ONE).coerceIn(
						valueRange.start,
						valueRange.endInclusive
					)
					onValueChange(newValue)
				}
		)
	}
}