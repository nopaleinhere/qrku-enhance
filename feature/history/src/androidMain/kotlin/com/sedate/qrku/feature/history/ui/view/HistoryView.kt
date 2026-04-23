package com.sedate.qrku.feature.history.ui.view

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.history.ui.components.HistoryItem
import com.sedate.qrku.feature.history.ui.components.SelectionTopBar
import com.sedate.qrku.feature.history.ui.components.historyList
import com.sedate.qrku.feature.history.viewmodel.HistoryUiEvent
import com.sedate.qrku.feature.history.viewmodel.HistoryViewModel
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.empty_img
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun HistoryView(
    navigator: Navigator,
    contentPadding: PaddingValues
) {
    val context = LocalContext.current
    val layoutDirection = LocalLayoutDirection.current
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
        val isLoading by isLoading.collectAsState()

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
            backgroundColor = colorScheme.background,
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
                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    historyData.isEmpty() -> {
                        EmptyState(contentPadding)
                    }

                    else -> {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize()
                                .padding(
                                    bottom = contentPadding.calculateBottomPadding(),
                                    start = it.calculateRightPadding(layoutDirection),
                                    end = it.calculateLeftPadding(layoutDirection)
                                ),
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
                }
            }
        )
    }
}

@Composable
private fun EmptyState(contentPadding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = contentPadding.calculateBottomPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(Res.drawable.empty_img),
            contentDescription = null,
            modifier = Modifier.size(SeDimen.Dp160)
        )
        Spacer(Modifier.height(SeDimen.Dp16))
        Text(
            text = "No Records Found",
            style = typography.bodyLarge,
            color = colorScheme.onSurfaceVariant
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