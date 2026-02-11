package com.sedate.qrku.feature.history.ui.view

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.core.ui.theme.Grey100
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.history.ui.components.HistoryItem
import com.sedate.qrku.feature.history.ui.components.SelectionTopBar
import com.sedate.qrku.feature.history.ui.components.historyList
import com.sedate.qrku.feature.history.viewmodel.HistoryUiEvent
import com.sedate.qrku.feature.history.viewmodel.HistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun HistoryView(
	navigator: Navigator,
	contentPadding: PaddingValues
) {
	val context = LocalContext.current
	val viewModel: HistoryViewModel = koinViewModel()

	val listState = rememberSaveable(
		saver = LazyListState.Saver
	) {
		LazyListState()
	}

	with(viewModel) {
		val historyData by historyData.collectAsState()
		val selectedIds by selectedIds.collectAsState()
		val isSelectionMode by isSelectionMode.collectAsState()

		LaunchedEffect(Unit) {
			getHistory()
			triggerToast(
				context,
				viewModel
			)
		}

		BackHandler(enabled = isSelectionMode) {
			clearSelection()
		}

		BaseUi(
			backgroundColor = Grey100,
			appBar = {
				SelectionTopBar(
					selectedCount = selectedIds.size,
					isSelectionMode = isSelectionMode,
					onSelectAll = {
						selectAll()
					},
					onDelete = {
						deleteSelected()
					},
					onClose = {
						clearSelection()
					}
				)
			},
			content = {
				LazyColumn(
					state = listState,
					modifier = Modifier.fillMaxSize()
						.padding(bottom = contentPadding.calculateBottomPadding()),
					contentPadding = PaddingValues(vertical = SeDimen.Dp16),
					verticalArrangement = Arrangement.spacedBy(SeDimen.Dp12)
				) {
					historyList(
						historyData,
						selectedIds,
					) { selected, history ->
						HistoryItem(
							history,
							selected = selected,
							selectionMode = isSelectionMode,
							onClick = {
								if (isSelectionMode) {
									toggleSelect(history.id)
								} else {
									navigator.navigate(
										OverviewRoute.QrCapture(
											actionType = BarcodeType.Action.HISTORY,
											type = history.contentType,
											input = history.result,
											format = history.formatCode,
											date = history.createdAt
										)
									)
								}
							},
							onLongClick = {
								toggleSelect(history.id)
							}
						)
					}
				}
			}
		)
	}
}

private suspend fun triggerToast(
	context: Context,
	viewModel: HistoryViewModel
): Nothing = with(viewModel) {
	uiEvent.collect { event ->
		when (event) {
			is HistoryUiEvent.ShowToast -> {
				Toast.makeText(
					context,
					event.message,
					Toast.LENGTH_SHORT
				)
					.show()
			}
		}
	}
}