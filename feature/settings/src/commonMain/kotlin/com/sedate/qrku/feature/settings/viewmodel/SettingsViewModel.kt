package com.sedate.qrku.feature.settings.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.state.UiState
import com.sedate.qrku.core.datastore.preference.app.AppSettingsPreferences
import com.sedate.qrku.core.datastore.preference.settings.SettingsPreference
import com.sedate.qrku.core.model.SeTheme
import com.sedate.qrku.core.model.SettingsData
import com.sedate.qrku.feature.settings.contract.AppInfoProvider
import com.sedate.qrku.feature.settings.contract.SettingsNavigator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
class SettingsViewModel(
	private val appSettingsPreference: AppSettingsPreferences,
	private val settingsPreference: SettingsPreference,
	private val appInfoProvider: AppInfoProvider,
	private val navigator: SettingsNavigator
) : ViewModel() {
	val uiState: StateFlow<UiState<SettingsData>> =
		settingsPreference.settingsFlow
			.map { state ->
				UiState.Success(
					state.copy(
						appVersion = appInfoProvider.getAppVersion(),
					)
				)
			}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.Eagerly,
				initialValue = UiState.Loading
			)

	fun toggleBeep(value: Boolean) {
		viewModelScope.launch {
			settingsPreference.setBeep(value)
		}
	}

	fun toggleVibrate(value: Boolean) {
		viewModelScope.launch {
			settingsPreference.setVibrate(value)
		}
	}

	fun toggleAutoOpen(value: Boolean) {
		viewModelScope.launch {
			settingsPreference.setAutoOpen(value)
		}
	}

	fun toggleConfirmBeforeOpen(value: Boolean) {
		viewModelScope.launch {
			settingsPreference.setConfirmOpen(value)
		}
	}

	fun toggleDarkMode(value: Boolean) {
		val themeMode = when (value) {
			true -> SeTheme.DARK
			false -> SeTheme.LIGHT
		}

		viewModelScope.launch {
			appSettingsPreference.setSeTheme(themeMode)
		}
	}

	fun openPrivacyPolicy() = navigator.openPrivacyPolicy()

	fun contactDeveloper() = navigator.contactDeveloper()

	fun rateApp() = navigator.rateApp()
}