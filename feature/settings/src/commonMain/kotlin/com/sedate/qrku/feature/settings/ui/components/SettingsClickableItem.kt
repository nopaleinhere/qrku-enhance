package com.sedate.qrku.feature.settings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
			.padding(horizontal = SeDimen.Dp16),
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.bodyLarge
		)

		subtitle?.let {
			Spacer(modifier = Modifier.height(SeDimen.Dp4))
			Text(
				text = it,
				style = MaterialTheme.typography.bodyMedium,
				color = Grey600
			)
		}
	}
}