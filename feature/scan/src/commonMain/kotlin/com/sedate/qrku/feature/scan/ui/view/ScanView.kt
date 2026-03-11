package com.sedate.qrku.feature.scan.ui.view

import androidx.compose.runtime.Composable
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.feature.scan.viewmodel.ScanViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
expect fun ScanView(
	navigator: Navigator,
	viewModel: ScanViewModel= koinViewModel()
)