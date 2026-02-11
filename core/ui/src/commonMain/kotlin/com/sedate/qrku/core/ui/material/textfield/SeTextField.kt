package com.sedate.qrku.core.ui.material.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst
import com.sedate.qrku.core.common.constants.SeConst.TWELVE
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SeTextField(
	type: BarcodeType.Support,
	state: TextFieldState,
	modifier: Modifier = Modifier,
	prefixSelector: (String) -> Unit
) {
	var scheme by remember { mutableStateOf(SeConst.HTTPS) }

	val config = remember(type) {
		SeTextFieldAttr.of(type)
	}

	LaunchedEffect(scheme) {
		if (type == BarcodeType.Support.LINK)
			prefixSelector(scheme)
	}

	HandleUpcInput(
		type,
		state
	)

	Column(modifier) {
		OutlinedTextField(
			state = state,
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(config.minHeight),
			placeholder = { Text(config.placeholder) },
			lineLimits = config.lineLimits,
			keyboardOptions = config.keyboardOptions,
			leadingIcon = when (config.leadingIconType) {
				LeadingIconType.SchemeSelector -> {
					{
						SchemeDropdown(
							selected = scheme,
							onSelected = {
								scheme = it
							}
						)
					}
				}

				LeadingIconType.None -> null
			},
			colors = OutlinedTextFieldDefaults.colors(
				focusedBorderColor = MaterialTheme.colorScheme.primary,
				unfocusedBorderColor = MaterialTheme.colorScheme.outline,
				errorBorderColor = MaterialTheme.colorScheme.error,
				disabledBorderColor = MaterialTheme.colorScheme.outlineVariant,

				focusedLabelColor = MaterialTheme.colorScheme.primary,
				unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
				errorLabelColor = MaterialTheme.colorScheme.error,

				cursorColor = MaterialTheme.colorScheme.primary
			)
		)

		config.helper?.let {
			Spacer(Modifier.height(SeDimen.Dp6))
			HelperRow(
				helper = it,
				count = state.text.length,
				max = config.maxLength
			)
		}
	}
}

@Composable
private fun HandleUpcInput(
	type: BarcodeType.Support,
	state: TextFieldState
) {
	if (type != BarcodeType.Support.UPC_A) return

	LaunchedEffect(type) {
		snapshotFlow { state.text }
			.collect { text ->
				val filtered = text
					.filter(Char::isDigit)
					.take(Int.TWELVE)

				if (filtered != text) {
					state.edit {
						replace(
							Int.ZERO,
							length,
							filtered
						)
					}
				}
			}
	}
}

@Composable
private fun HelperRow(
	helper: String,
	count: Int,
	max: Int
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = helper,
			style = MaterialTheme.typography.bodySmall,
			color = MaterialTheme.colorScheme.onSurfaceVariant
		)

		Text(
			text = "$count / $max",
			style = MaterialTheme.typography.bodySmall
		)
	}
}

@Composable
private fun SchemeDropdown(
	selected: String,
	onSelected: (String) -> Unit
) {
	var expanded by remember { mutableStateOf(false) }

	Box(
		modifier = Modifier
			.height(SeDimen.Dp48)
			.clickable { expanded = true },
		contentAlignment = Alignment.Center
	) {
		Text(
			text = selected,
			modifier = Modifier
				.padding(horizontal = SeDimen.Dp16),
			style = MaterialTheme.typography.bodyMedium
		)
	}

	DropdownMenu(
		expanded = expanded,
		onDismissRequest = { expanded = false }
	) {
		listOf(
			SeConst.HTTPS,
			SeConst.HTTP
		).forEach {
			DropdownMenuItem(
				text = { Text(it) },
				onClick = {
					onSelected(it)
					expanded = false
				}
			)
		}
	}
}