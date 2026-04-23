package com.sedate.qrku.feature.overview.ui.view

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.FIVE_HUNDRED_TWELVE
import com.sedate.qrku.core.common.utils.extractDomain
import com.sedate.qrku.core.common.utils.isValidUrl
import com.sedate.qrku.core.common.utils.normalizeUrl
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.material.dialog.SeBottomDialog
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.utils.ParsedContent
import com.sedate.qrku.core.ui.utils.QrGenerator
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.typeFormat
import com.sedate.qrku.feature.overview.ui.components.ActionButton
import com.sedate.qrku.feature.overview.ui.components.BarcodeDescription
import com.sedate.qrku.feature.overview.ui.components.ResultDescription
import com.sedate.qrku.feature.overview.viewmodel.QrCaptureViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
actual fun QrCaptureView(
    navigator: Navigator,
    actionType: BarcodeType.Action,
    type: String,
    input: String,
    format: Int?,
    date: Long?
) {
    val viewModel: QrCaptureViewModel = koinViewModel()

    with(viewModel) {
        val uriHandler = LocalUriHandler.current
        val contentType = remember { mutableStateOf(String.EMPTY) }

        val qrImage: MutableState<Bitmap?> = remember { mutableStateOf(null) }

        LaunchedEffect(
            input,
            format
        ) {
            QrGenerator.generate(
                text = input,
                format = format,
                size = Int.FIVE_HUNDRED_TWELVE,
                onType = {
                    contentType.value = when (it) {
                        is ParsedContent.Text -> "Text"
                        is ParsedContent.Url -> "Url"
                        is ParsedContent.Product -> "Product"
                        is ParsedContent.Wifi -> "Wifi"
                        is ParsedContent.Contact -> "Contact"
                        is ParsedContent.Phone -> "Phone"
                        is ParsedContent.Email -> "Email"
                        is ParsedContent.Sms -> "Sms"
                        is ParsedContent.Calendar -> "Calendar"
                    }
                },
                onCapture = {
                    qrImage.value = it
                }
            )
        }

        LaunchedEffect(contentType) {
            if (contentType.value.isNotEmpty() && actionType != BarcodeType.Action.HISTORY) {
                saveResult(
                    actionType = actionType,
                    value = input,
                    contentType = contentType.value,
                    format = typeFormat(format),
                    formatCode = format
                )
            }
        }

        ConfirmUrlDialog(
            viewModel = viewModel,
            openLink = {
                if (input.isValidUrl()) uriHandler.openUri(input)
            }
        )

        BaseUi(
            appBar = {
                SeAppBar(
                    state = AppBarState(
                        title = when (actionType) {
                            BarcodeType.Action.SCAN -> "Scan Overview"
                            BarcodeType.Action.CREATE -> "Create Overview"
                            BarcodeType.Action.HISTORY -> "History Overview"
                        },
                        type = AppBarType.SUB_LEVEL
                    ),
                    onBackClick = {
                        navigator.pop()
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = SeDimen.Dp16)
                ) {
                    ResultDescription(
                        input,
                        viewModel
                    )

                    BarcodeDescription(
                        qrImage = qrImage.value,
                        contentType = contentType.value,
                        type = type,
                        format = format,
                        date = date
                    )

                    ActionButton(qrImage.value)
                }
            }
        )
    }
}

@Composable
fun ConfirmUrlDialog(
    viewModel: QrCaptureViewModel,
    openLink: (String) -> Unit
) = with(viewModel) {
    val confirmUrl by confirmUrl.collectAsState()

    confirmUrl?.let { url ->
        val normalized = normalizeUrl(url)
        val domain = extractDomain(normalized)

        SeBottomDialog(
            icon = Icons.Default.OpenInBrowser,
            title = "Open link?",
            message = domain,
            description = normalized,
            onConfirm = {
                openLink(normalized)
                dismissDialog()
            },
            onDismiss = {
                dismissDialog()
            }
        )
    }
}