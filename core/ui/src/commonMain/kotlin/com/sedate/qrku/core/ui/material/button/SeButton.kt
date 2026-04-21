package com.sedate.qrku.core.ui.material.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SeButton(
	text: String,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	loading: Boolean = false,
	onClick: () -> Unit
) {
	ElevatedButton(
		onClick = onClick,
		enabled = enabled && loading.not(),
		modifier = modifier
			.fillMaxWidth()
			.height(SeDimen.Dp42),
		shape = RoundedCornerShape(SeDimen.Dp6),
		elevation = ButtonDefaults.elevatedButtonElevation(
			defaultElevation = SeDimen.Dp2,
			pressedElevation = SeDimen.Dp0
		),
		colors = ButtonDefaults.elevatedButtonColors(
			containerColor = colorScheme.secondary,
			contentColor = colorScheme.onSecondary,
		)
	) {
		if (loading) {
			CircularProgressIndicator(
				strokeWidth = SeDimen.Dp2,
				modifier = Modifier.size(SeDimen.Dp16),
				color = colorScheme.onPrimary
			)
		} else {
			Text(
				text = text,
				style = SeTextStyle.ButtonSmall
			)
		}
	}
}
