package com.sedate.qrku.feature.overview.ui.components

import android.content.ClipData
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonShapes
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.text.style.TextDecoration
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TEN
import com.sedate.qrku.core.common.utils.isValidUrl
import com.sedate.qrku.core.ui.theme.SeTextStyle
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.overview.viewmodel.QrCaptureViewModel
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.copy_label
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun ResultDescription(
	input: String,
	viewModel: QrCaptureViewModel
) = with(viewModel) {
	val localClipboard = LocalClipboard.current
	val scope = rememberCoroutineScope()
	val scrollState = rememberScrollState()
	val clipData = ClipData.newPlainText(
		stringResource(Res.string.copy_label),
		input
	)
	val uriHandler = LocalUriHandler.current
	val settingsData by settingsData.collectAsState()

	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp8),
		verticalAlignment = Alignment.Top
	) {
		Box(
			modifier = Modifier
				.weight(Float.ONE)
				.heightIn(
					max = with(LocalDensity.current) {
						(typography.bodyLarge.lineHeight * Int.TEN).toDp()
					}
				)
				.verticalScroll(scrollState)
		) {
			if (input.isValidUrl()) {
				Text(
					text = input,
					style = typography.bodyLarge.copy(
						color = colorScheme.primary,
						textDecoration = TextDecoration.Underline
					),
					modifier = Modifier.clickable {
						if (settingsData.isConfirmBeforeOpenEnabled) {
							showConfirmDialog(input)
						} else {
							uriHandler.openUri(input)
						}
					}
				)
			} else {
				Text(
					text = input,
					style = typography.bodyLarge,
				)
			}
		}

		IconButton(
			onClick = {
				scope.launch { localClipboard.setClipEntry(clipData.toClipEntry()) }
			},
			shapes = IconButtonShapes(
				shape = RoundedCornerShape(SeDimen.Dp16)
			),
			content = {
				Icon(
					imageVector = Icons.Default.ContentCopy,
					contentDescription = null
				)
			}
		)
	}
}