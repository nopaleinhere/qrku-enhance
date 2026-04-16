package com.sedate.qrku.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sedate.qrku.AppState
import com.sedate.qrku.core.common.constants.SeConst.TWO_HUNDRED
import com.sedate.qrku.feature.history.navigation.historyFlow
import com.sedate.qrku.feature.overview.navigation.overviewFlow
import com.sedate.qrku.feature.scan.navigation.scanFlow
import com.sedate.qrku.feature.settings.navigation.settingsFlow
import com.sedate.qrku.feature.write.navigation.writeFlow

@Composable
fun RootNavGraph(
	appState: AppState,
	innerPadding: PaddingValues
) {
	SharedTransitionLayout {
		NavDisplay(
			backStack = appState.navBackStack,
			onBack = { appState.navigator.pop() },

			entryDecorators = listOf(
				rememberSaveableStateHolderNavEntryDecorator(),
				rememberViewModelStoreNavEntryDecorator()
			),
			entryProvider = entryProvider {
				writeFlow(appState.navigator)
				scanFlow(appState.navigator)
				overviewFlow(appState.navigator)
				historyFlow(appState.navigator, innerPadding)
				settingsFlow(innerPadding)
			},
			transitionSpec = {
				fadeIn(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				) togetherWith fadeOut(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				)
			},
			popTransitionSpec = {
				fadeIn(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				) togetherWith fadeOut(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				)
			},
			predictivePopTransitionSpec = {
				fadeIn(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				) togetherWith fadeOut(
					animationSpec = tween(durationMillis = Int.TWO_HUNDRED)
				)
			}
		)
	}
}