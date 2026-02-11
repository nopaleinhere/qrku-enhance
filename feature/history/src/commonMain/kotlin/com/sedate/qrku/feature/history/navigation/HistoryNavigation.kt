package com.sedate.qrku.feature.history.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.NavDisplay
import com.sedate.qrku.core.ui.navigation.HistoryRoute
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.feature.history.ui.view.HistoryView

fun EntryProviderScope<Routes>.historyFlow(
	navigator: Navigator,
	innerPadding: PaddingValues
) {
	entry<HistoryRoute.History>(
		metadata = NavDisplay.transitionSpec {
			EnterTransition.None togetherWith ExitTransition.None
		} + NavDisplay.popTransitionSpec {
			EnterTransition.None togetherWith ExitTransition.None
		}
	) {
		HistoryView(navigator, innerPadding)
	}
}