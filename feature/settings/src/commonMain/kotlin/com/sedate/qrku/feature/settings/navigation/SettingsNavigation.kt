package com.sedate.qrku.feature.settings.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.NavDisplay
import com.sedate.qrku.core.common.state.UiState
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.core.ui.navigation.SettingsRoute
import com.sedate.qrku.feature.settings.ui.view.AboutView
import com.sedate.qrku.feature.settings.ui.view.SettingsView
import com.sedate.qrku.feature.settings.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<Routes>.settingsFlow(
	//	navigator: Navigator,
	innerPadding: PaddingValues
) {
	entry<SettingsRoute.Settings>(metadata = NavDisplay.transitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	} + NavDisplay.popTransitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	}) {
		val viewModel: SettingsViewModel = koinViewModel()
		val uiState by viewModel.uiState.collectAsStateWithLifecycle()

		when (val state = uiState) {
			is UiState.Loading -> {}
			is UiState.Success -> {
				SettingsView(
					contentPadding = innerPadding,
					settingsData = state.data,
					viewModel = viewModel
				)
			}

			else -> {}
		}
	}

	entry<SettingsRoute.About> {
		AboutView()
	}
}
