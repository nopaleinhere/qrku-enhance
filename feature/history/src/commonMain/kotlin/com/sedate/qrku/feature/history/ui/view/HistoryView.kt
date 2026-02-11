package com.sedate.qrku.feature.history.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.sedate.qrku.core.ui.navigation.Navigator

@Composable
expect fun HistoryView(
	navigator: Navigator,
	contentPadding: PaddingValues
)