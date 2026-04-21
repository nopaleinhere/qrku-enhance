package com.sedate.qrku.feature.history.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.ui.theme.Grey600
import com.sedate.qrku.core.ui.theme.lightGray
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.combinedSafeClickable
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.link_ic
import org.jetbrains.compose.resources.painterResource

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
				colorScheme.secondaryContainer
			else
				lightGray
		),
		elevation = CardDefaults.cardElevation(SeDimen.Dp2)
	) {
		Row(
			modifier = Modifier.padding(
				horizontal = SeDimen.Dp16,
				vertical = SeDimen.Dp4
			),
			verticalAlignment = Alignment.CenterVertically
		) {
			if (selectionMode) {
				Checkbox(
					checked = selected,
					onCheckedChange = null
				)
				Spacer(Modifier.width(SeDimen.Dp8))
			} else {
				Box(
					Modifier
						.background(
							colorScheme.surface,
							shape = RoundedCornerShape(SeDimen.Dp6)
						)
						.clip(RoundedCornerShape(SeDimen.Dp6))
						.padding(SeDimen.Dp12)

				) {
					Image(
						painterResource(Res.drawable.link_ic),
						contentDescription = null,
						modifier = Modifier.size(SeDimen.Dp24)
					)
				}
			}

			Column(
				modifier = Modifier.padding(SeDimen.Dp12)
			) {
				Text(
					text = history.result,
					style = typography.bodyLarge
				)

				Spacer(Modifier.height(SeDimen.Dp8))

				Text(
					text = "${history.contentType} (${history.actionType}) | ${history.format}",
					style = typography.labelMedium,
					color = Grey600
				)
			}
		}
	}
}