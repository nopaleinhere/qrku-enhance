package com.sedate.qrku

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sedate.qrku.core.datastore.preference.app.AppSettingsPreferences
import com.sedate.qrku.core.model.AppLanguage
import com.sedate.qrku.core.model.SeTheme
import com.sedate.qrku.core.ui.shared.ComposePlugin
import com.sedate.qrku.core.ui.shared.ProvideShared
import com.sedate.qrku.navigation.RootNavGraph
import com.sedate.qrku.ui.Localization
import com.sedate.qrku.ui.NavigationBar
import com.sedate.qrku.ui.Theme
import org.koin.compose.koinInject

@Composable
fun App() {
	val appState: AppState = rememberAppState()
	val preferencesManager: AppSettingsPreferences = koinInject()

	val selectedLanguage by preferencesManager.getAppLanguageCode()
		.collectAsStateWithLifecycle(AppLanguage.ENGLISH.code)

	val selectedTheme by preferencesManager.getSeTheme()
		.collectAsStateWithLifecycle(SeTheme.SYSTEM.value)

	val localizationPlugin: ComposePlugin = { content ->
		Localization(selectedLanguage = selectedLanguage) {
			content()
		}
	}

	val themePlugin: ComposePlugin = { content ->
		Theme(selectedTheme) {
			content()
		}
	}

	ProvideShared(
		plugins = listOf(
			localizationPlugin,
			themePlugin
		)
	) {
		Scaffold(
			bottomBar = {
				NavigationBar(appState = appState)
			}
		) { innerPadding ->
			RootNavGraph(appState, innerPadding)
		}
	}
}