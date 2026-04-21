package com.sedate.qrku.feature.write.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.write.data.FormPart
import com.sedate.qrku.feature.write.viewmodel.FormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicForm(
	formState: FormState,
	fields: List<FormPart>
) {
	Column(verticalArrangement = Arrangement.spacedBy(SeDimen.Dp12)) {
		fields.forEach { field ->
			when (field) {
				is FormPart.TextField -> {
					InputTextField(
						state = formState,
						field = field
					)
				}

				is FormPart.Dropdown -> {
					InputDropdown(
						state = formState,
						field = field
					)
				}

				is FormPart.DateTimeField -> {
					DateTimeField(
						label = field.label,
						value = formState.values[field.key].orEmpty(),
						onValueChange = {
							formState.touched[field.key] = true
						},
						onDisplayChange = {
							formState.values[field.key] = it
						}
					)
				}
			}
		}
	}
}

@Composable
private fun InputTextField(
	state: FormState,
	field: FormPart.TextField
) {
	val formValue = state.values[field.key].orEmpty()
	val errorDesc = state.errors[field.key]
	val isTouched = state.touched[field.key] == true
	val showError = isTouched && errorDesc != null
	val protocol = state.values["protocol"] ?: "https://"

	Column {
		OutlinedTextField(
			value = formValue,
			onValueChange = { input ->
				val cleanInput = input
					.removePrefix("http://")
					.removePrefix("https://")

				val newValue = field.maxLength?.let {
					cleanInput.take(it)
				} ?: cleanInput

				state.values[field.key] = newValue
				state.touched[field.key] = true

				state.errors[field.key] =
					if (isTouched) field.validator(newValue) else null
			},
			label = { Text(field.label) },
			prefix = {
				if (field.key == "url") Text(
					protocol,
					color = colorScheme.onSurfaceVariant
				)
			},
			isError = showError,
			keyboardOptions = KeyboardOptions(
				keyboardType = field.keyboardType
			),
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(SeDimen.Dp4))

		if (showError || field.maxLength != null)
			HelperForm(
				field = field,
				formValue = formValue,
				errorDesc = errorDesc.orEmpty(),
				showError = showError
			)
	}
}

@Composable
private fun InputDropdown(
	state: FormState,
	field: FormPart.Dropdown
) {
	val value = state.values[field.key].orEmpty()
	var expanded by remember { mutableStateOf(false) }

	Column {

		Text(
			text = field.label,
			style = typography.bodyMedium
		)

		Spacer(modifier = Modifier.height(SeDimen.Dp4))

		Box {

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.clip(RoundedCornerShape(SeDimen.Dp12))
					.background(colorScheme.surfaceVariant)
					.clickable { expanded = true }
					.padding(
						horizontal = SeDimen.Dp16,
						vertical = SeDimen.Dp14
					),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {

				Text(
					text = value.ifEmpty { "Select ${field.label}" },
					color = if (value.isEmpty())
						colorScheme.onSurfaceVariant
					else
						colorScheme.onSurface
				)

				Icon(
					imageVector = Icons.Default.ArrowDropDown,
					contentDescription = null
				)
			}

			DropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false }
			) {
				field.options.forEach { option ->
					DropdownMenuItem(
						text = { Text(option) },
						onClick = {
							state.values[field.key] = option
							expanded = false
						}
					)
				}
			}
		}
	}
}

@Composable
private fun HelperForm(
	field: FormPart.TextField,
	formValue: String,
	errorDesc: String,
	showError: Boolean
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		val helperText = when {
			showError -> errorDesc
			formValue.isEmpty() -> field.description.orEmpty()
			else -> String.EMPTY
		}

		Text(
			text = helperText,
			color = when {
				showError -> colorScheme.error
				else -> colorScheme.onSurfaceVariant
			},
			style = typography.bodySmall
		)

		field.maxLength?.let { max ->
			Text(
				text = "${formValue.length} / $max",
				style = typography.bodySmall,
				color = when {
					showError -> colorScheme.error
					formValue.length == max -> colorScheme.primary
					else -> colorScheme.onSurfaceVariant
				}
			)
		}
	}
}