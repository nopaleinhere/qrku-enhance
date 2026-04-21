package com.sedate.qrku.feature.write.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun TabBar(
	selectedIndex: Int,
	onSelected: (Int) -> Unit
) {
	val density = LocalDensity.current
	val textWidths = remember { mutableStateListOf<Dp>() }

	val tabs = listOf(
		"QR Code",
		"Barcode"
	)

	SecondaryTabRow(
		selectedIndex,
		Modifier,
		TabRowDefaults.primaryContainerColor,
		TabRowDefaults.primaryContentColor,
		indicator = {
			val textWidth = (textWidths.getOrNull(selectedIndex)
				?.minus(SeDimen.Dp6)) ?: SeDimen.Dp0

			TabRowDefaults.PrimaryIndicator(
				modifier = Modifier.tabIndicatorOffset(selectedIndex),
				width = textWidth,
				color = colorScheme.secondary
			)
		},
		tabs = {
			tabs.forEachIndexed { index, title ->
				Tab(
					selected = selectedIndex == index,
					onClick = { onSelected(index) },
					text = {
						Text(
							title,
							color = if (selectedIndex == index) {
								colorScheme.secondary
							} else {
								colorScheme.onSurfaceVariant
							},
							onTextLayout = { result ->
								val width = with(density) {
									result.size.width.toDp()
								}

								if (textWidths.size > index) {
									textWidths[index] = width
								} else {
									textWidths.add(width)
								}
							}
						)
					}
				)
			}
		}
	)
}
