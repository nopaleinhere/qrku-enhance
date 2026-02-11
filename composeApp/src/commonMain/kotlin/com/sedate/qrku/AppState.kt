package com.sedate.qrku

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sedate.qrku.core.ui.navigation.NavigationState
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.core.ui.navigation.SeDestination
import com.sedate.qrku.core.ui.navigation.WriteRoute

@Stable
class AppState(
	val navBackStack: SnapshotStateList<Routes>,
) {
	val navigator: Navigator = NavigationState(navBackStack)
	val destination: List<SeDestination> = SeDestination.entries
	private val bottomBarRoutes = destination.map { it.route }

	val currentTopLevelDestination: SeDestination?
		@Composable get() {
			return destination.firstOrNull { topLevelDestination ->
				navBackStack.last() == topLevelDestination.route
			}
		}

	@Composable
	fun shouldShowBottomBar(): Boolean {
		return navBackStack.last() in bottomBarRoutes
	}
}

@Composable
fun rememberAppState(
	navBackStack: SnapshotStateList<Routes> = remember { mutableStateListOf(WriteRoute.Landing) }
): AppState {
	return remember(navBackStack) {
		AppState(navBackStack = navBackStack)
	}
}