package com.sedate.qrku.feature.write.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.theme.Grey100
import com.sedate.qrku.feature.write.ui.components.QrMenuGrid
import com.sedate.qrku.feature.write.ui.components.TabBar
import com.sedate.qrku.feature.write.viewmodel.WriteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WriteView(
	navigator: Navigator,
	viewModel: WriteViewModel = koinViewModel()
) = with(viewModel) {
	var selectedTab by remember { mutableStateOf(Int.ZERO) }

	val currentCategory = if (selectedTab == Int.ZERO) BarcodeType.Category.QR
	else BarcodeType.Category.BARCODE

	val data = getByCategory(currentCategory)

	BaseUi(
		backgroundColor = Grey100,
		appBar = {
			SeAppBar(
				AppBarState(
					"Generate QRKU",
					type = AppBarType.TOP_LEVEL
				)
			)
		},
		tabBar = {
			TabBar(
				selectedIndex = selectedTab,
				onSelected = { selectedTab = it })
		},
		content = {
			AnimatedContent(targetState = data) { list ->
				QrMenuGrid(
					navigator = navigator,
					items = list,
					viewModel = viewModel
				)
			}
		})
}