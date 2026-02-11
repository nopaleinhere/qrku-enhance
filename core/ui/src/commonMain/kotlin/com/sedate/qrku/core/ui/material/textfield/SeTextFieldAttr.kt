package com.sedate.qrku.core.ui.material.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.TWELVE
import com.sedate.qrku.core.ui.utils.SeDimen

data object SeTextFieldAttr {
	fun of(type: BarcodeType.Support): TextFieldConfig =
		when (type) {
			BarcodeType.Support.TEXT ->
				text()

			BarcodeType.Support.LINK ->
				link()

			BarcodeType.Support.UPC_A ->
				upcA()

			else -> text()
		}

	private fun text() = TextFieldConfig(
		minHeight = SeDimen.Dp180,
		placeholder = "Enter text",
		helper = null,
		maxLength = Int.MAX_VALUE,
		lineLimits = TextFieldLineLimits.MultiLine()
	)

	private fun link() = TextFieldConfig(
		minHeight = SeDimen.Dp56,
		placeholder = "Enter link",
		helper = null,
		maxLength = Int.MAX_VALUE,
		lineLimits = TextFieldLineLimits.SingleLine,
		leadingIconType = LeadingIconType.SchemeSelector
	)

	private fun upcA() = TextFieldConfig(
		minHeight = SeDimen.Dp56,
		placeholder = "Enter UPC A",
		helper = "Masukkan 12 digit angka",
		maxLength = Int.TWELVE,
		lineLimits = TextFieldLineLimits.SingleLine,
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Number
		)
	)
}

data class TextFieldConfig(
	val minHeight: Dp,
	val placeholder: String,
	val helper: String?,
	val maxLength: Int,
	val lineLimits: TextFieldLineLimits,
	val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
	val leadingIconType: LeadingIconType = LeadingIconType.None
)

sealed class LeadingIconType {
	data object None : LeadingIconType()
	data object SchemeSelector : LeadingIconType()
}