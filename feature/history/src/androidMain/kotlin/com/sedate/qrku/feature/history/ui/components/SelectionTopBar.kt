package com.sedate.qrku.feature.history.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SelectionTopBar(
	selectedCount: Int,
	isSelectionMode: Boolean,
	onSelectAll: () -> Unit,
	onDelete: () -> Unit,
	onClose: () -> Unit
) {
	if (isSelectionMode) {
		TopAppBar(
			title = { Text("$selectedCount selected") },
			navigationIcon = {
				IconButton(onClick = onClose) {
					Icon(
						Icons.Default.Close,
						null
					)
				}
			},
			actions = {
				IconButton(onClick = onSelectAll) {
					Icon(
						Icons.Default.SelectAll,
						null
					)
				}
				IconButton(onClick = onDelete) {
					Icon(
						Icons.Default.Delete,
						null
					)
				}
			}
		)
	} else {
		SeAppBar(
			AppBarState(
				"History QRKU",
				AppBarType.TOP_LEVEL
			)
		)
	}
}