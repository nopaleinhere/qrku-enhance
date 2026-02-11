package com.sedate.qrku.feature.overview.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.sedate.qrku.core.common.utils.getCurrentDateTimeFormatted
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.typeFormat

@Suppress("ParamsComparedByRef")
@Composable
internal fun BarcodeDescription(
	qrImage: Bitmap?,
	contentType: String,
	type: String,
	format: Int?,
	date: Long?
) {
	val currentDate = getCurrentDateTimeFormatted(date)
	val typeFormat = typeFormat(format)

	Column(modifier = Modifier.padding(top = SeDimen.Dp16)) {
		Text(
			type.ifEmpty { contentType },
			style = SeTextStyle.H5
		)
		Text(
			"$currentDate | $typeFormat",
			style = SeTextStyle.BodyLarge,
			color = colorScheme.onSurfaceVariant
		)
	}

	Card(
		modifier = Modifier.padding(top = SeDimen.Dp8),
		colors = CardDefaults.cardColors(
			containerColor = colorScheme.background
		),
		elevation = CardDefaults.cardElevation(
			defaultElevation = SeDimen.Dp4
		)
	) {
		Box(
			modifier = Modifier.fillMaxWidth()
				.padding(SeDimen.Dp24),
			contentAlignment = Alignment.Center
		) {
			qrImage?.let {
				Image(
					bitmap = it.asImageBitmap(),
					contentDescription = null,
				)
			}
		}
	}
}