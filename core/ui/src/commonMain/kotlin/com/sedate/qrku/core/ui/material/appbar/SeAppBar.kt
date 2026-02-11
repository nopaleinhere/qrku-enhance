package com.sedate.qrku.core.ui.material.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.sedate.qrku.core.ui.utils.SeDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeAppBar(
	state: AppBarState,
	modifier: Modifier = Modifier,
	onBackClick: () -> Unit = {},
) {
	TopAppBar(
		modifier = modifier,
		title = {
			state.type.style?.let { textStyle ->
				Text(
					text = state.title,
					style = textStyle,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
			}
		},
		navigationIcon = {
			if (state.type == AppBarType.SUB_LEVEL) {
				IconButton(
					onClick = onBackClick,
					modifier = Modifier.size(SeDimen.Dp40)
				) {
					Icon(
						imageVector = Icons.Filled.ArrowBackIosNew,
						contentDescription = "BACK_IC"
					)
				}
			}
		},
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = colorScheme.background
		)
	)
}