package com.sedate.qrku.feature.overview.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.core.ui.navigation.Routes
import com.sedate.qrku.feature.overview.ui.view.QrCaptureView

fun EntryProviderScope<Routes>.overviewFlow(navigator: Navigator) {
    entry<OverviewRoute.QrCapture> {
        QrCaptureView(
            navigator = navigator,
            actionType = it.actionType,
            type = it.type,
            input = it.input,
            format = it.format,
            date = it.date,
        )
    }
}

