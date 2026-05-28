package com.sedate.qrku.feature.scan.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.NavDisplay
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.core.ui.navigation.ScanRoute
import com.sedate.qrku.feature.scan.ui.view.ScanView

fun EntryProviderScope<Routes>.scanFlow(
	navigator: Navigator,
	innerPadding: PaddingValues
) {
	entry<ScanRoute.Scan>(metadata = NavDisplay.transitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	} + NavDisplay.popTransitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	}) {
		ScanView(navigator, innerPadding)
	}
}