package com.sedate.qrku.core.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.safeClickable(
	enabled: Boolean = true,
	delayMillis: Long = 600L,
	onClick: () -> Unit
): Modifier = composed {
	var isClickable by remember { mutableStateOf(true) }
	val scope = rememberCoroutineScope()

	clickable(
		enabled = enabled && isClickable
	) {
		isClickable = false
		onClick()

		scope.launch {
			delay(delayMillis)
			isClickable = true
		}
	}
}

fun Modifier.combinedSafeClickable(
	enabled: Boolean = true,
	role: Role? = null,
	debounceMillis: Long = 400L,
	onClick: () -> Unit,
	onLongClick: (() -> Unit)? = null
): Modifier = composed {

	val interactionSource = remember { MutableInteractionSource() }
	var isEnabled by remember { mutableStateOf(true) }

	fun safeClick(block: () -> Unit) {
		if (!isEnabled) return
		isEnabled = false
		block()
	}

	LaunchedEffect(isEnabled) {
		if (isEnabled.not()) {
			delay(debounceMillis)
			isEnabled = true
		}
	}

	combinedClickable(
		enabled = enabled && isEnabled,
		role = role,
		interactionSource = interactionSource,
		onClick = {
			safeClick(onClick)
		},
		onLongClick = {
			onLongClick?.invoke()
		}
	)
}