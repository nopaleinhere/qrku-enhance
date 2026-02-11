package com.sedate.qrku.feature.write.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.utils.BarcodeValidator
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.material.button.SeButton
import com.sedate.qrku.core.ui.material.textfield.SeTextField
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.write.viewmodel.GenerateViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GenerateView(
	navigator: Navigator,
	type: String,
	subType: BarcodeType.Support,
	viewModel: GenerateViewModel = koinViewModel()
) = with(viewModel) {
	val textFieldState = remember { TextFieldState() }
	val prefixText = remember { mutableStateOf(String.EMPTY) }

	BaseUi(
		appBar = {
			SeAppBar(
				state = AppBarState(
					title = "Make $type - ${subType.text}",
					type = AppBarType.SUB_LEVEL
				),
				onBackClick = {
					navigator.pop()
				}
			)
		},
		content = {
			SeTextField(
				type = subType,
				state = textFieldState,
				modifier = Modifier.padding(top = SeDimen.Dp16),
				prefixSelector = {
					prefixText.value = it
				}
			)
		},
		bottomBar = {
			SeButton(
				text = "Generate",
				enabled = BarcodeValidator.isValid(
					textFieldState.text.toString(),
					subType
				),
				onClick = {
					navigator.navigate(
						OverviewRoute.QrCapture(
							actionType = BarcodeType.Action.CREATE,
							type = subType.contentType,
							input = "${prefixText.value}${textFieldState.text}",
							format = subType.format
						)
					)
				}
			)
		}
	)
}