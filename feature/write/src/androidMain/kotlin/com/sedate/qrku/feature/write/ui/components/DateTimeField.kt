package com.sedate.qrku.feature.write.ui.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.ui.utils.SeDimen
import java.util.Calendar

@Composable
actual fun DateTimeField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit,
	onDisplayChange: (String) -> Unit
) {
	val context = LocalContext.current
	val calendar = Calendar.getInstance()

	val datePicker = DatePickerDialog(
		context,
		{ _, year, month, day ->

			val timePicker = TimePickerDialog(
				context,
				{ _, hour, minute ->

					val raw = String.format(
						"%04d%02d%02dT%02d%02d00",
						year,
						month + Int.ONE,
						day,
						hour,
						minute
					)

					val display = formatDisplayDate(
						year,
						month,
						day,
						hour,
						minute
					)

					onValueChange(raw)

					onDisplayChange(display)

				},
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE),
				true
			)

			timePicker.show()

		},
		calendar.get(Calendar.YEAR),
		calendar.get(Calendar.MONTH),
		calendar.get(Calendar.DAY_OF_MONTH)
	)

	Column {
		Text(
			label,
			style = typography.bodyMedium
		)

		Spacer(modifier = Modifier.height(SeDimen.Dp4))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.clip(RoundedCornerShape(SeDimen.Dp12))
				.background(colorScheme.surfaceVariant)
				.clickable { datePicker.show() }
				.padding(SeDimen.Dp16)
		) {
			Text(
				text = value.ifEmpty { "Select $label" },
				color = if (value.isEmpty()) colorScheme.onSurfaceVariant
				else colorScheme.onSurface
			)
		}
	}
}

@SuppressLint("DefaultLocale")
private fun formatDisplayDate(
	year: Int,
	month: Int,
	day: Int,
	hour: Int,
	minute: Int
): String {

	val monthNames = listOf(
		"Jan", "Feb", "Mar", "Apr", "Mei", "Jun",
		"Jul", "Agu", "Sep", "Okt", "Nov", "Des"
	)

	val formattedHour = String.format("%02d:%02d", hour, minute)

	return "$day ${monthNames[month]} $year, $formattedHour"
}