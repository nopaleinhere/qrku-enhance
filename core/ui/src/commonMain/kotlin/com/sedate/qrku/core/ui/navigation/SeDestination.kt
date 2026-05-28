package com.sedate.qrku.core.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class SeDestination(
	val icon: ImageVector,
	val route: Routes,
) {
	SCAN(
		icon = Icons.Filled.QrCodeScanner,
		route = ScanRoute.Scan
	),
	WRITE(
		icon = Icons.Filled.Edit,
		route = WriteRoute.Write
	),
	HISTORY(
		icon = Icons.Filled.History,
		route = HistoryRoute.History
	),
	SETTINGS(
		icon = Icons.Filled.Settings,
		route = SettingsRoute.Settings
	)
}