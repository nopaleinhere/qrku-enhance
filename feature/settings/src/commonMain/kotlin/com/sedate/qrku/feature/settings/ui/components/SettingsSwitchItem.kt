package com.sedate.qrku.feature.settings.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.ui.material.toggle.SeToggle
import com.sedate.qrku.core.ui.theme.Grey600
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.safeClickable
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.open_link_ic
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingsSwitchItem(
	title: String,
	icon: Painter,
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.safeClickable { onCheckedChange(checked.not()) }
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					horizontal = SeDimen.Dp16,
					vertical = SeDimen.Dp14
				),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Image(
					icon,
					contentDescription = null,
					modifier = Modifier.size(SeDimen.Dp26)
				)
				Spacer(Modifier.width(SeDimen.Dp12))
				Text(
					text = title,
					style = typography.bodyMedium
				)
			}
			SeToggle(
				checked = checked,
				onCheckedChange = onCheckedChange,
			)
		}
		HorizontalDivider()
	}
}