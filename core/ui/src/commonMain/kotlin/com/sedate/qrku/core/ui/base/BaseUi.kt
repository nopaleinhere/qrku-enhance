package com.sedate.qrku.core.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.SeDimen.Dp16

@Composable
fun BaseUi(
    backgroundColor: Color? = null,
    appBar: (@Composable () -> Unit)? = null,
    tabBar: (@Composable () -> Unit)? = null,
    content: (@Composable (PaddingValues) -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null
) {
    val density = LocalDensity.current

    val bottomBarHeight = remember { mutableStateOf(SeDimen.Dp0) }

    val contentPadding = PaddingValues(
        bottom = bottomBarHeight.value + SeDimen.Dp32,
        start = Dp16,
        end = Dp16
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (backgroundColor != null) {
                    Modifier.background(backgroundColor)
                } else Modifier
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            appBar?.invoke()
            tabBar?.invoke()

            Box(
                modifier = Modifier
                    .weight(Float.ONE)
                    .fillMaxWidth()
                    .imePadding()
            ) {
                content?.invoke(contentPadding)
            }
        }

        bottomBar?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(colorScheme.background)
                    .windowInsetsPadding(
                        WindowInsets.navigationBars.union(WindowInsets.ime)
                    )
                    .padding(Dp16)
                    .onGloballyPositioned { v ->
                        bottomBarHeight.value = with(density) {
                            v.size.height.toDp()
                        }
                    }
            ) {
                it()
            }
        }
    }
}