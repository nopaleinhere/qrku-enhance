package com.sedate.qrku.core.ui.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sedate.qrku.core.common.constants.SeConst.ONE

class NavigationState(
	private val navBackStack: SnapshotStateList<Routes>
) : Navigator {
	override fun navigate(route: Routes) {
		navBackStack.add(route)
	}

	override fun pop() {
		if (navBackStack.size > Int.ONE) {
			navBackStack.removeLast()
		}
	}

	override fun navigateToTopLevelDestination(destination: SeDestination) {
		val root = when (destination) {
			SeDestination.WRITE -> WriteRoute.Landing
			SeDestination.SCAN -> ScanRoute.Scan
			SeDestination.HISTORY -> HistoryRoute.History
		}

		if (navBackStack.lastOrNull() == root) return

		resetTo(root)
	}

	override fun replace(route: Routes) {
		if (navBackStack.isNotEmpty()) {
			navBackStack.removeLast()
		}
		navBackStack.add(route)
	}

	override fun resetTo(route: Routes) {
		navBackStack.clear()
		navBackStack.add(route)
	}
}