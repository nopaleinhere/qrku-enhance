package com.sedate.qrku.core.ui.navigation

import androidx.compose.runtime.Stable

@Stable
interface Navigator {
	fun navigate(route: Routes)
	fun pop()
	fun navigateToTopLevelDestination(destination: SeDestination)
	fun replace(route: Routes)
	fun resetTo(route: Routes)
}