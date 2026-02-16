package com.sedate.qrku.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.AppState
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.TWO_HUNDRED
import com.sedate.qrku.core.ui.navigation.SeDestination
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.history
import com.sedate.qrku.resources.scan
import com.sedate.qrku.resources.settings
import com.sedate.qrku.resources.write
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationBar(
	modifier: Modifier = Modifier,
	appState: AppState,
) {
	AnimatedVisibility(
		visible = appState.shouldShowBottomBar(),
		enter = slideInVertically(
			initialOffsetY = { it / Int.TWO }
		) + fadeIn(animationSpec = tween(durationMillis = Int.TWO_HUNDRED)),
		exit = slideOutVertically(
			targetOffsetY = { it / Int.TWO }
		) + fadeOut(animationSpec = tween(durationMillis = Int.TWO_HUNDRED))
	) {
		NavigationBar(
			modifier = modifier,
			tonalElevation = SeDimen.Dp0,
			content = {
				appState.destination.forEach { destination ->
					val selected = appState.currentTopLevelDestination == destination
					NavigationBarItem(
						selected = selected,
						onClick = { appState.navigator.navigateToTopLevelDestination(destination) },
						icon = {
							Icon(
								imageVector = destination.icon,
								contentDescription = null,
							)
						},
						label = { Text(stringResource(getStringRes(destination))) },
					)
				}
			},
		)
	}
}

private fun getStringRes(destination: SeDestination): StringResource {
	return when (destination) {
		SeDestination.WRITE -> Res.string.write
		SeDestination.SCAN -> Res.string.scan
		SeDestination.HISTORY -> Res.string.history
		SeDestination.SETTINGS -> Res.string.settings
	}
}