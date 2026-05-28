package com.sedate.qrku.feature.settings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SettingsSection(
	title: String,
	content: @Composable ColumnScope.() -> Unit
) {
	Column {
		Text(
			text = title,
			style = typography.labelLarge,
			color = colorScheme.onSurfaceVariant,
			modifier = Modifier.padding(horizontal = SeDimen.Dp16)
		)
		Spacer(Modifier.height(SeDimen.Dp12))
		content()
	}
}