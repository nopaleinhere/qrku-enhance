package com.sedate.qrku.core.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.shared.SharedScope
import com.sedate.qrku.core.ui.shared.LocalShared
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun BaseUi(
	backgroundColor: Color? = null,
	appBar: (@Composable () -> Unit)? = null,
	content: (@Composable () -> Unit)? = null,
	bottomBar: (@Composable () -> Unit)? = null
) = Box(
	modifier = Modifier.fillMaxSize() then (if (backgroundColor != null) {
		Modifier.background(backgroundColor)
	} else {
		Modifier
	})
) {
	Column(
		modifier = Modifier.fillMaxSize()
	) {
		appBar?.invoke()
		content?.let {
			Box(
				modifier = Modifier.weight(Float.ONE)
					.fillMaxWidth()
					.padding(horizontal = SeDimen.Dp16)
			) {
				content()
			}
		}
	}

	bottomBar?.let {
		Box(
			modifier = Modifier.align(Alignment.BottomCenter)
				.background(colorScheme.background)
				.fillMaxWidth()
				.windowInsetsPadding(
					WindowInsets.navigationBars.union(WindowInsets.ime)
				)
				.padding(
					horizontal = SeDimen.Dp16,
					vertical = SeDimen.Dp16
				),
		) {
			bottomBar()
		}
	}
}