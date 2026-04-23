package com.sedate.qrku.feature.write.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.sedate.qrku.core.common.constants.SeConst.THREE
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.WriteRoute
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.feature.write.data.GenerateType
import com.sedate.qrku.feature.write.viewmodel.WriteViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MenuGrid(
	navigator: Navigator,
	items: List<GenerateType>,
	viewModel: WriteViewModel = koinViewModel()
) = with(viewModel) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(Int.THREE),
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp16),
		verticalArrangement = Arrangement.spacedBy(SeDimen.Dp24),
		contentPadding = PaddingValues(top = SeDimen.Dp16)
	) {
		items(items) { item ->
			MenuItem(
				item = item,
				icon = item.iconKey.toDrawable(),
				onClick = {
					navigator.navigate(WriteRoute.Generate(
						type = item.support.contentType,
						support = item.support
					))
				}
			)
		}
	}
}

@Composable
private fun MenuItem(
	item: GenerateType,
	icon: DrawableResource?,
	onClick: () -> Unit = {},
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.clip(RoundedCornerShape(SeDimen.Dp6))
			.clickable { onClick() }
	) {
		Box(
			modifier = Modifier
				.size(
					width = SeDimen.Dp80,
					height = SeDimen.Dp86
				)
				.padding(top = SeDimen.Dp6)
				.clip(RoundedCornerShape(SeDimen.Dp6))
				.background(colorScheme.surfaceVariant),
			contentAlignment = Alignment.Center
		) {
			icon?.let {
				Image(
					painterResource(it),
					contentDescription = item.support.text,
					modifier = Modifier.size(SeDimen.Dp32),
				)
			}
		}

		Spacer(modifier = Modifier.height(SeDimen.Dp8))

		Text(
			text = item.support.text,
			style = typography.bodyMedium,
		)
	}
}
