package com.sedate.qrku.feature.write.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.ui.theme.Grey600
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.safeClickable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CategoryTileComponent(
	text: String,
	desc: String,
	icon: DrawableResource,
	onClick: () -> Unit
) {
	Card(
		modifier = Modifier.fillMaxWidth()
			.padding(vertical = SeDimen.Dp8)
			.clip(CardDefaults.shape)
			.safeClickable {
				onClick()
			},
		elevation = CardDefaults.cardElevation(SeDimen.Dp2)
	) {
		Row(
			modifier = Modifier.fillMaxWidth()
				.height(IntrinsicSize.Min),
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				modifier = Modifier.fillMaxHeight()
					.width(SeDimen.Dp70)
					.background(color = colorScheme.secondaryContainer),
				contentAlignment = Alignment.Center
			) {
				Icon(
					painterResource(icon),
					contentDescription = null,
					tint = colorScheme.onPrimary,
					modifier = Modifier.size(SeDimen.Dp24),
				)
			}
			Column(
				Modifier.padding(SeDimen.Dp16),
				verticalArrangement = Arrangement.spacedBy(SeDimen.Dp4)
			) {
				Text(
					text,
					style = SeTextStyle.H5
				)
				if (desc.isNotEmpty()) {
					Text(
						desc,
						style = SeTextStyle.BodyMedium,
						color = Grey600
					)
				}
			}
		}
	}
}