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
import com.sedate.qrku.feature.write.ui.view.LandingView

fun EntryProviderScope<Routes>.writeFlow(
	navigator: Navigator,
) {
	entry<WriteRoute.Landing>(metadata = NavDisplay.transitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	} + NavDisplay.popTransitionSpec {
		EnterTransition.None togetherWith ExitTransition.None
	}) {
		LandingView(navigator)
	}

	entry<WriteRoute.Generate> {
		GenerateView(
			navigator = navigator,
			type = it.type,
			subType = it.subType
		)
	}
}
