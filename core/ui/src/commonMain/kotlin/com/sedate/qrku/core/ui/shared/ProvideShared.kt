package com.sedate.qrku.core.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.sedate.qrku.core.common.shared.SharedScope
import org.koin.compose.koinInject

typealias ComposePlugin = @Composable (@Composable () -> Unit) -> Unit

@Composable
fun ProvideShared(
	plugins: List<ComposePlugin> = emptyList(),
	content: @Composable () -> Unit
) {
	val sharedViewModel: SharedScope = koinInject()

	CompositionLocalProvider(
		LocalShared provides sharedViewModel
	) {
		ApplyPlugins(
			plugins,
			content
		)
	}
}

@Composable
private fun ApplyPlugins(
	plugins: List<ComposePlugin>,
	content: @Composable () -> Unit
) {
	var current: @Composable () -> Unit = content

	plugins.reversed()
		.forEach { plugin ->
			val prev = current
			current = { plugin(prev) }
		}

	current()
}

val LocalShared = compositionLocalOf<SharedScope> {
	error("SharedScope not provided")
}