package com.sedate.qrku.core.ui.material.toggle

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.sedate.qrku.core.common.constants.SeConst.FOUR_HUNDRED
import com.sedate.qrku.core.common.constants.SeConst.ONE_HUNDRED
import com.sedate.qrku.core.common.constants.SeConst.ONE_HUNDRED_FIFTY
import com.sedate.qrku.core.common.constants.SeConst.TWO_HUNDRED_FIFTY
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_SEVEN
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SeToggle(
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier
) {
	val transition = updateTransition(
		checked,
		label = "switch"
	)

	val thumbOffset by transition.animateDp(
		label = "thumb_offset",
		transitionSpec = {
			spring(
				dampingRatio = Float.ZERO_POINT_SEVEN,
				stiffness = Float.FOUR_HUNDRED
			)
		}
	) { if (it) SeDimen.Dp23 else SeDimen.Dp3 }

	val trackColor by transition.animateColor(
		label = "track_color",
		transitionSpec = { tween(Int.TWO_HUNDRED_FIFTY) }
	) { if (it) colorScheme.secondary else colorScheme.surfaceVariant }

	val thumbColor by transition.animateColor(
		label = "thumb_color",
		transitionSpec = { tween(Int.TWO_HUNDRED_FIFTY) }
	) { if (it) colorScheme.surface else colorScheme.onSurfaceVariant }

	val thumbWidth by transition.animateDp(
		label = "thumb_width",
		transitionSpec = { tween(Int.ONE_HUNDRED_FIFTY) }
	) { SeDimen.Dp24 }

	Box(
		modifier = modifier
			.width(SeDimen.Dp50)
			.height(SeDimen.Dp26)
			.clip(RoundedCornerShape(SeDimen.Dp14))
			.background(trackColor)
			.clickable(
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			) { onCheckedChange(checked.not()) },
		contentAlignment = Alignment.CenterStart
	) {
		Box(
			modifier = Modifier
				.padding(start = thumbOffset)
				.size(
					width = thumbWidth,
					height = SeDimen.Dp22
				)
				.clip(RoundedCornerShape(Int.ONE_HUNDRED))
				.background(thumbColor)
				.shadow(
					elevation = SeDimen.Dp4,
					shape = CircleShape
				)
		)
	}
}