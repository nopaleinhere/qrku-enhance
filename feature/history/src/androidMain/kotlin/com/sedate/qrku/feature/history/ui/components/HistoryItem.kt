package com.sedate.qrku.feature.history.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.ui.theme.Grey600
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.combinedSafeClickable

@Composable
internal fun HistoryItem(
	history: BarcodeHistory,
	selected: Boolean,
	selectionMode: Boolean,
	onClick: () -> Unit,
	onLongClick: () -> Unit
) {
	Card(
		modifier = Modifier.fillMaxWidth()
			.clip(CardDefaults.shape)
			.combinedSafeClickable(
				onClick = onClick,
				onLongClick = onLongClick
			),
		colors = CardDefaults.cardColors(
			containerColor = if (selected)
				MaterialTheme.colorScheme.secondaryContainer
			else
				MaterialTheme.colorScheme.surface
		),
		elevation = CardDefaults.cardElevation(SeDimen.Dp2)
	) {
		Row(
			modifier = Modifier.padding(SeDimen.Dp12),
			verticalAlignment = Alignment.CenterVertically
		) {
			if (selectionMode) {
				Checkbox(
					checked = selected,
					onCheckedChange = null
				)
				Spacer(Modifier.width(SeDimen.Dp8))
			}

			Column(
				modifier = Modifier.padding(SeDimen.Dp12)
			) {
				Text(
					text = history.result,
					style = MaterialTheme.typography.bodyLarge
				)

				Spacer(Modifier.height(SeDimen.Dp4))

				Text(
					text = "${history.contentType} (${history.actionType}) | ${history.format}",
					style = MaterialTheme.typography.labelMedium,
					color = Grey600
				)
			}
		}
	}
}