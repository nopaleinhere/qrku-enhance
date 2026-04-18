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
import com.sedate.qrku.feature.write.viewmodel.WriteViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WriteView(
	navigator: Navigator,
	viewModel: WriteViewModel = koinViewModel()
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
				groupedData.forEach { (type, data) ->
					stickyHeader {
						TextDivider(type)
					}

					items(
						items = data,
						key = { it.format }
					) { v ->
						v.iconKey.toDrawable()
							?.let { icon ->
								CategoryTileComponent(
									text = v.format.text,
									desc = stringResource(v.desc),
									icon = icon,
									onClick = {
										navigator.navigate(
											WriteRoute.Generate(
												type = type,
												subType = v.format
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