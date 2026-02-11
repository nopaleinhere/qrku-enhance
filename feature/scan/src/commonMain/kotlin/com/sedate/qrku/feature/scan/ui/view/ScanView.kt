package com.sedate.qrku.feature.scan.ui.view

import androidx.compose.runtime.Composable
import com.sedate.qrku.core.ui.navigation.Navigator

@Composable
expect fun ScanView(
	navigator: Navigator,
)