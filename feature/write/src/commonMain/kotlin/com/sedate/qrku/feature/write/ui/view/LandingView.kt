package com.sedate.qrku.feature.write.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedate.qrku.core.ui.base.BaseUi
import com.sedate.qrku.core.ui.material.appbar.AppBarState
import com.sedate.qrku.core.ui.material.appbar.AppBarType
import com.sedate.qrku.core.ui.material.appbar.SeAppBar
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.WriteRoute
import com.sedate.qrku.core.ui.theme.Grey100
import com.sedate.qrku.feature.write.ui.components.CategoryTileComponent
import com.sedate.qrku.feature.write.ui.components.TextDivider
import com.sedate.qrku.feature.write.viewmodel.LandingViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingView(
	navigator: Navigator,
	viewModel: LandingViewModel = koinViewModel()
) = with(viewModel) {
	BaseUi(
		backgroundColor = Grey100,
		appBar = {
			SeAppBar(
				AppBarState(
					"Make Barcode & QR Code",
					type = AppBarType.TOP_LEVEL
				)
			)
		},
		content = {
			LazyColumn(
				Modifier.fillMaxSize()
			) {
				groupedData.forEach { (type, items) ->
					stickyHeader {
						TextDivider(type)
					}

					items(
						items,
						key = { it.format }
					) { data ->
						data.iconKey.toDrawable()
							?.let { icon ->
								CategoryTileComponent(
									text = data.format.text,
									desc = stringResource(data.desc),
									icon = icon,
									onClick = {
										navigator.navigate(
											WriteRoute.Generate(
												type = type,
												subType = data.format
											)
										)
									}
								)
							}
					}
				}
			}
		}
	)
}