package com.sedate.qrku.feature.overview.ui.components

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconButtonShapes
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.saveBitmapToGallery
import com.sedate.qrku.core.ui.utils.shareBitmap

@Suppress("ParamsComparedByRef")
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun ActionButton(
	qrImage: Bitmap?
) {
	val context = LocalContext.current

	Row(
		modifier = Modifier.padding(top = SeDimen.Dp16),
		horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp16)
	) {
		IconButton(
			modifier = Modifier.size(SeDimen.Dp50),
			shapes = IconButtonShapes(
				shape = RoundedCornerShape(SeDimen.Dp16)
			),
			colors = IconButtonDefaults.iconButtonColors(
				contentColor = colorScheme.onSurface
			),
			content = {
				Icon(
					imageVector = Icons.Default.Save,
					contentDescription = null,
					modifier = Modifier.size(SeDimen.Dp32),
				)
			},
			onClick = {
				qrImage?.let {
					saveBitmapToGallery(
						context,
						it
					)

					Toast.makeText(
						context,
						"Image saved to gallery",
						Toast.LENGTH_SHORT
					).show()
				}

			},
		)
		IconButton(
			modifier = Modifier.size(SeDimen.Dp50),
			shapes = IconButtonShapes(
				shape = RoundedCornerShape(SeDimen.Dp16)
			),
			content = {
				Icon(
					imageVector = Icons.Default.Share,
					contentDescription = null,
					modifier = Modifier.size(SeDimen.Dp32),
				)
			},
			onClick = {
				qrImage?.let {
					shareBitmap(
						context,
						it
					)
				}
			},
		)
	}
}