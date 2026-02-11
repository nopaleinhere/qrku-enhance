package com.sedate.qrku.feature.write.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
internal fun TextDivider(text: String) {
	Row(
		modifier = Modifier.fillMaxWidth()
			.padding(
				top = SeDimen.Dp26,
				bottom = SeDimen.Dp12
			),
		verticalAlignment = Alignment.CenterVertically
	) {
		HorizontalDivider(
			modifier = Modifier.weight(Float.ONE),
			color = colorScheme.onSurface
		)

		Text(
			text = text,
			modifier = Modifier.padding(horizontal = SeDimen.Dp12),
			style = SeTextStyle.H5,
			color = colorScheme.onSurface
		)

		HorizontalDivider(
			modifier = Modifier.weight(Float.ONE),
			color = colorScheme.onSurface
		)
	}
}