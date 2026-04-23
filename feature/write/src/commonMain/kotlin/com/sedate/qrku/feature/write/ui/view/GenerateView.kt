package com.sedate.qrku.feature.write.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.material.button.SeButton
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.feature.write.data.CodeEncoder
import com.sedate.qrku.feature.write.data.FormSchemaRegistry
import com.sedate.qrku.feature.write.data.mapToContent
import com.sedate.qrku.feature.write.ui.components.DynamicForm
import com.sedate.qrku.feature.write.viewmodel.FormState
import com.sedate.qrku.feature.write.viewmodel.GenerateViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GenerateView(
    navigator: Navigator,
    type: String,
    support: BarcodeType.Support,
    viewModel: GenerateViewModel = koinViewModel()
) = with(viewModel) {
    val layoutDirection = LocalLayoutDirection.current
    val formState = rememberFormState(support)

    var selectedType by remember {
        mutableStateOf(support)
    }

    val fields = remember(selectedType) {
        FormSchemaRegistry.get(selectedType)
    }

    BaseUi(
        appBar = {
            SeAppBar(
                state = AppBarState(
                    title = "Make $type - ${support.text}",
                    type = AppBarType.SUB_LEVEL
                ),
                onBackClick = {
                    navigator.pop()
                }
            )
        },
        content = {
            DynamicForm(
                formState = formState,
                fields = fields,
                modifier = Modifier.padding(
                    start = it.calculateLeftPadding(layoutDirection),
                    end = it.calculateRightPadding(layoutDirection),
                    bottom = it.calculateBottomPadding()
                )
            )
        },
        bottomBar = {
            SeButton(
                text = "Generate",
                enabled = validateForm(
                    fields,
                    formState
                ),
                onClick = {
                    val valid = validateForm(fields, formState)
                    if (valid.not()) return@SeButton

                    val content = mapToContent(selectedType, formState.values)

                    navigator.navigate(
                        OverviewRoute.QrCapture(
                            actionType = BarcodeType.Action.CREATE,
                            type = support.contentType,
                            input = CodeEncoder.encode(content),
                            format = support.format
                        )
                    )
                }
            )
        }
    )
}

@Composable
private fun rememberFormState(type: BarcodeType.Support): FormState {
    val state = remember { FormState() }

    LaunchedEffect(type) {
        state.values.clear()
        state.errors.clear()

        if (type == BarcodeType.Support.LINK) {
            state.values["protocol"] = "https://"
        }
    }

    return state
}