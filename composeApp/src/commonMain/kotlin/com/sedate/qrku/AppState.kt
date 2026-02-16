package com.sedate.qrku

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sedate.qrku.core.ui.navigation.NavigationState
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.core.ui.navigation.SeDestination
import com.sedate.qrku.core.ui.navigation.WriteRoute

@Stable
class AppState {
	val navigator = NavigationState()

	val navBackStack: SnapshotStateList<Routes>
		get() = navigator.navBackStack

	val destination: List<SeDestination> = SeDestination.entries

	val currentTopLevelDestination: SeDestination
		@Composable get() = navigator.currentTab

	@Composable
	fun shouldShowBottomBar(): Boolean {
		return navBackStack.lastOrNull() in destination.map { it.route }
	}
}

@Composable
fun rememberAppState(): AppState {
	return remember { AppState() }
}