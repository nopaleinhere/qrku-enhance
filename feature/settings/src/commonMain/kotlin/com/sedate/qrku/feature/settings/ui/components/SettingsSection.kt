package com.sedate.qrku.feature.settings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SettingsSection(
	title: String,
	content: @Composable ColumnScope.() -> Unit
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(SeDimen.Dp12)
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.titleMedium
		)

		Card(
			shape = RoundedCornerShape(SeDimen.Dp16),
			modifier = Modifier.fillMaxWidth()
		) {
			Column(
				modifier = Modifier.padding(vertical = SeDimen.Dp16),
				verticalArrangement = Arrangement.spacedBy(SeDimen.Dp16),
				content = content
			)
		}
	}
}