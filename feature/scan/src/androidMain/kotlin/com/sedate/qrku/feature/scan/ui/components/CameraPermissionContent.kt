package com.sedate.qrku.feature.scan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.material.button.SeButton
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.camera_img
import org.jetbrains.compose.resources.painterResource

@Composable
fun CameraPermissionContent(
	onRequest: () -> Unit,
	onOpenSettings: () -> Unit,
	permanentlyDenied: Boolean
) {
	ButtonColors(
		containerColor = colorScheme.onTertiaryContainer,
		contentColor = colorScheme.onPrimary,
		disabledContainerColor = colorScheme.onSurface,
		disabledContentColor = colorScheme.onPrimary
	)

	Card(modifier = Modifier.padding(top = SeDimen.Dp16)) {
		Column(
			modifier = Modifier.padding(SeDimen.Dp16),
			verticalArrangement = Arrangement.spacedBy(SeDimen.Dp10)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp10)
			) {
				Image(
					painterResource(Res.drawable.camera_img),
					contentDescription = null,
					modifier = Modifier.size(SeDimen.Dp95)
				)
				Column(verticalArrangement = Arrangement.SpaceBetween) {
					Text(
						"Enable Permission to Continue",
						style = typography.labelLarge
					)
					Spacer(Modifier.height(SeDimen.Dp12))
					Text(
						"This feature won’t work without the required permission. You can enable it in your app settings.",
						style = typography.bodySmall
					)
				}
			}
			SeButton(
				text = if (permanentlyDenied.not()) "Allow Permission" else "Open Settings",
				onClick = {
					if (permanentlyDenied.not()) {
						onRequest()
					} else {
						onOpenSettings()
					}
				}
			)
		}
	}
}