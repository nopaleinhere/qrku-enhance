package com.sedate.qrku.feature.history.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.ui.utils.SeDimen

internal fun LazyListScope.historyList(
	historyData: Map<String, List<BarcodeHistory>>,
	selectedIds: Set<Long>,
	content: @Composable (Boolean, BarcodeHistory) -> Unit
) = with(this) {
	historyData.forEach { (date, items) ->
		item(key = date) {
			Text(
				text = date,
				style = typography.bodyMedium,
				modifier = Modifier.padding(vertical = SeDimen.Dp8)
			)
		}

		items(
			items,
			key = { it.id }
		) { history ->
			val selected = history.id in selectedIds

			content.invoke(
				selected,
				history
			)
		}
	}
}