package com.sedate.qrku.feature.write.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.NavDisplay
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.core.ui.navigation.WriteRoute
import com.sedate.qrku.feature.write.ui.view.GenerateView
import com.sedate.qrku.feature.write.ui.view.WriteView

fun EntryProviderScope<Routes>.writeFlow(
	navigator: Navigator,
) {
	entry<WriteRoute.Write>(metadata = NavDisplay.transitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	} + NavDisplay.popTransitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	}) {
		WriteView(navigator)
	}

	entry<WriteRoute.Generate> {
		GenerateView(
			navigator = navigator,
			type = it.type,
			support = it.support
		)
	}
}
