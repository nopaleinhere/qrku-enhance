package com.sedate.qrku.core.ui.material.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.ZER0_POINT_SIX
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_FIFTEN
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_FIVE
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_FOUR
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_FOURTY_EIGHT
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SeBottomDialog(
	icon: ImageVector,
	title: String,
	message: String,
	description: String? = null,
	confirmText: String = "Open",
	cancelText: String = "Cancel",
	onConfirm: () -> Unit,
	onDismiss: () -> Unit
) {
	Dialog(
		onDismissRequest = onDismiss,
		properties = DialogProperties(
			usePlatformDefaultWidth = false
		)
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.Black.copy(alpha = Float.ZERO_POINT_FOURTY_EIGHT))
				.clickable(
					indication = null,
					interactionSource = remember { MutableInteractionSource() }
				) { onDismiss() },
			contentAlignment = Alignment.BottomCenter
		) {
			Surface(
				modifier = Modifier
					.fillMaxWidth()
					.clickable(enabled = false) {},
				shape = RoundedCornerShape(
					topStart = SeDimen.Dp20,
					topEnd = SeDimen.Dp20
				),
				color = MaterialTheme.colorScheme.background,
				tonalElevation = SeDimen.Dp0
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(SeDimen.Dp24)
				) {
					Box(
						modifier = Modifier
							.size(SeDimen.Dp48)
							.background(
								color = MaterialTheme.colorScheme.primaryContainer,
								shape = RoundedCornerShape(SeDimen.Dp12)
							),
						contentAlignment = Alignment.Center
					) {
						Icon(
							imageVector = icon,
							contentDescription = null,
							tint = MaterialTheme.colorScheme.primary,
							modifier = Modifier.size(SeDimen.Dp22)
						)
					}

					Spacer(Modifier.height(SeDimen.Dp16))

					Text(
						text = title,
						style = MaterialTheme.typography.titleLarge
					)

					Spacer(Modifier.height(SeDimen.Dp6))

					Text(
						text = message,
						style = MaterialTheme.typography.titleSmall,
						color = MaterialTheme.colorScheme.onSurface.copy(alpha = Float.ZER0_POINT_SIX)
					)

					description?.let {
						Spacer(Modifier.height(SeDimen.Dp4))
						Text(
							text = it,
							style = MaterialTheme.typography.titleSmall,
							color = MaterialTheme.colorScheme.onSurface.copy(alpha = Float.ZERO_POINT_FOUR),
							maxLines = Int.TWO,
							overflow = TextOverflow.Ellipsis
						)
					}

					Spacer(Modifier.height(SeDimen.Dp16))

					Box(
						modifier = Modifier
							.fillMaxWidth()
							.background(
								color = MaterialTheme.colorScheme.surfaceVariant,
								shape = RoundedCornerShape(SeDimen.Dp8)
							)
							.padding(
								horizontal = SeDimen.Dp12,
								vertical = SeDimen.Dp10
							)
					) {
						Text(
							text = "Be careful with unknown links",
							style = MaterialTheme.typography.labelLarge,
							color = MaterialTheme.colorScheme.onSurface.copy(alpha = Float.ZERO_POINT_FIVE)
						)
					}

					Spacer(Modifier.height(SeDimen.Dp24))

					Button(
						onClick = onConfirm,
						modifier = Modifier
							.fillMaxWidth()
							.height(SeDimen.Dp52),
						shape = RoundedCornerShape(SeDimen.Dp12)
					) {
						Text(
							text = confirmText,
							style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
						)
					}

					Spacer(Modifier.height(SeDimen.Dp10))

					OutlinedButton(
						onClick = onDismiss,
						modifier = Modifier
							.fillMaxWidth()
							.height(SeDimen.Dp52),
						shape = RoundedCornerShape(SeDimen.Dp12)
					) {
						Text(
							text = cancelText,
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}
			}
		}
	}
}