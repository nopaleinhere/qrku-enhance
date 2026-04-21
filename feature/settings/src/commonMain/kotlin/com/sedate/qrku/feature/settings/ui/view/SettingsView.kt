package com.sedate.qrku.feature.settings.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.model.SettingsData
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.theme.Grey100
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.settings.ui.components.SettingsClickableItem
import com.sedate.qrku.feature.settings.ui.components.SettingsSection
import com.sedate.qrku.feature.settings.ui.components.SettingsSwitchItem
import com.sedate.qrku.feature.settings.viewmodel.SettingsViewModel
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.open_link_ic
import com.sedate.qrku.resources.verif_link_ic
import com.sedate.qrku.resources.vibrate_ic
import com.sedate.qrku.resources.volume_ic
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingsView(
	contentPadding: PaddingValues,
	settingsData: SettingsData,
	viewModel: SettingsViewModel
) = with(viewModel) {
	BaseUi(
		backgroundColor = Grey100,
		autoVerticalPadding = false,
		appBar = {
			SeAppBar(
				state = AppBarState(
					title = "Settings QRKU",
					type = AppBarType.TOP_LEVEL
				),
			)
		},
		content = {
			LazyColumn(
				modifier = Modifier.fillMaxSize()
					.padding(bottom = contentPadding.calculateBottomPadding()),
				verticalArrangement = Arrangement.spacedBy(SeDimen.Dp24)
			) {
				item {
					SettingsSection(title = "Scan Settings") {
						SettingsSwitchItem(
							title = "Beep Sound",
							icon = painterResource(Res.drawable.volume_ic),
							checked = settingsData.isBeepEnabled,
							onCheckedChange = { toggleBeep(it) }
						)

						SettingsSwitchItem(
							title = "Vibrate",
							icon = painterResource(Res.drawable.vibrate_ic),
							checked = settingsData.isVibrateEnabled,
							onCheckedChange = { toggleVibrate(it) }
						)

						SettingsSwitchItem(
							title = "Open the link automatically",
							icon = painterResource(Res.drawable.open_link_ic),
							checked = settingsData.isAutoOpenEnabled,
							onCheckedChange = { toggleAutoOpen(it) }
						)

						SettingsSwitchItem(
							title = "Confirm opening the link",
							icon = painterResource(Res.drawable.verif_link_ic),
							checked = settingsData.isConfirmBeforeOpenEnabled,
							onCheckedChange = { toggleConfirmBeforeOpen(it) }
						)
					}
				}

				item {
					SettingsSection(title = "About the Application") {
						SettingsClickableItem(
							title = "Privacy Policy",
							onClick = { openPrivacyPolicy() }
						)

						SettingsClickableItem(
							title = "Contact Developer",
							onClick = { contactDeveloper() }
						)

						SettingsClickableItem(
							title = "Rate App",
							onClick = { rateApp() }
						)

						SettingsClickableItem(
							title = "Application Version",
							subtitle = settingsData.appVersion,
							onClick = {}
						)
					}
				}
			}
		}
	)
}