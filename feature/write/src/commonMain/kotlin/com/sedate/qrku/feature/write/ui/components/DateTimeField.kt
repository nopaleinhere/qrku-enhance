package com.sedate.qrku.feature.write.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun DateTimeField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit,
	onDisplayChange: (String) -> Unit
)