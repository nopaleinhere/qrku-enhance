package com.sedate.qrku.feature.write.data

import androidx.compose.ui.text.input.KeyboardType

sealed class FormPart {
	data class TextField(
		val key: String,
		val label: String,
		val required: Boolean = true,
		val keyboardType: KeyboardType = KeyboardType.Text,
		val maxLength: Int? = null,
		val description: String? = null,
		val validator: (String) -> String? = { null }
	) : FormPart()

	data class Dropdown(
		val key: String,
		val label: String,
		val options: List<String>,
		val required: Boolean = true
	) : FormPart()

	data class DateTimeField(
		val key: String,
		val label: String,
		val required: Boolean = true
	) : FormPart()
}