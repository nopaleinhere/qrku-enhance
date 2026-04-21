package com.sedate.qrku.feature.settings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.ui.theme.Grey600
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.safeClickable

@Composable
fun SettingsClickableItem(
	title: String,
	subtitle: String? = null,
	onClick: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.safeClickable { onClick() }
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					horizontal = SeDimen.Dp16,
					vertical = SeDimen.Dp14
				),
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				modifier = Modifier.weight(Float.ONE)
			) {
				Text(
					text = title,
					style = typography.bodyMedium
				)
				subtitle?.let {
					Spacer(modifier = Modifier.height(SeDimen.Dp4))
					Text(
						text = it,
						style = typography.bodySmall,
						color = Grey600
					)
				}
			}
			Icon(
				imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
				contentDescription = null,
				tint = colorScheme.onSurfaceVariant
			)
		}
		HorizontalDivider()
	}
}
