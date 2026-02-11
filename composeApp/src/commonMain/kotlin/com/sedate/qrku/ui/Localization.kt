package com.sedate.qrku.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.sedate.qrku.localization.LocalAppLocale

@Composable
fun Localization(selectedLanguage: String, content: @Composable () -> Unit) {
    val layoutDirection = LayoutDirection.Ltr

    CompositionLocalProvider(
        LocalAppLocale provides selectedLanguage,
        LocalLayoutDirection provides layoutDirection
    ) {
        key(selectedLanguage) {
            content()
        }
    }
}