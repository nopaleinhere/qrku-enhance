package com.sedate.qrku.feature.overview.ui.components

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.sedate.qrku.core.common.constants.SeConst.FIFTY_SIX
import com.sedate.qrku.core.common.constants.SeConst.FIVE_HUNDRED_TWELVE
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TWENTY_EIGHT
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_EIGHTEEN
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_TWENTY_FOUR
import com.sedate.qrku.core.common.utils.getCurrentDateTimeFormatted
import com.sedate.qrku.core.ui.theme.charcoalGray
import com.sedate.qrku.core.ui.theme.darkCharcoal
import com.sedate.qrku.core.ui.theme.dimGray
import com.sedate.qrku.core.ui.theme.electricPurple
import com.sedate.qrku.core.ui.theme.lightGreen
import com.sedate.qrku.core.ui.theme.midnightIndigo
import com.sedate.qrku.core.ui.theme.peach
import com.sedate.qrku.core.ui.utils.QrGenerator
import com.sedate.qrku.core.ui.utils.SeDimen
import com.sedate.qrku.core.ui.utils.saveBitmapToGallery
import com.sedate.qrku.core.ui.utils.shareBitmap
import com.sedate.qrku.core.ui.utils.typeFormat
import com.sedate.qrku.feature.overview.data.ButtonAction
import com.sedate.qrku.feature.overview.data.VCardData
import com.sedate.qrku.feature.overview.data.toVCardData
import kotlinx.coroutines.launch
@Suppress("ParamsComparedByRef")
@Composable
internal fun BarcodeDescription(
	qrImage: Bitmap?,
	raw: String,
	contentType: String,
	type: String,
	format: Int?,
	date: Long?,
) {
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	var captureVCard by remember { mutableStateOf<suspend () -> Bitmap?>({ null }) }
	val selected = remember { mutableStateOf(false) }
	val currentDate = getCurrentDateTimeFormatted(date)
	val typeFormat = typeFormat(format)
	
	Box(
		modifier = Modifier.fillMaxWidth(),
		contentAlignment = Alignment.Center
	) {
		Column(
			modifier = Modifier
				.clip(RoundedCornerShape(SeDimen.Dp20))
				.background(
					brush = Brush.verticalGradient(
						colors = listOf(
							electricPurple,
							midnightIndigo
						)
					)
				)
				.padding(SeDimen.Dp24),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			SwitchBox(
				raw = raw,
				contentType = contentType,
				format = format,
				qrImage = qrImage,
				selected = selected,
				onCaptureReady = { capture ->
					captureVCard = capture
				}
			)
			
			Spacer(Modifier.height(SeDimen.Dp16))
			
			Text(
				text = "${
					type
						.ifEmpty { contentType }
						.uppercase()
				} | $typeFormat",
				style = typography.titleMedium,
				color = colorScheme.surface,
				fontWeight = FontWeight.Bold
			)
			
			Text(
				text = currentDate,
				style = typography.bodyMedium,
				color = colorScheme.surface
			)
			
			Spacer(Modifier.height(SeDimen.Dp28))
			
			ButtonSegmented(
				onSave = {
					scope.launch {
						val bitmapToSave = if (selected.value) {
							captureVCard()
						} else {
							qrImage
						}
						
						bitmapToSave?.let {
							saveBitmapToGallery(context, it)
							
							Toast
								.makeText(
									context,
									"Image saved to gallery",
									Toast.LENGTH_SHORT
								)
								.show()
						}
					}
				},
				onShare = {
					qrImage?.let {
						shareBitmap(
							context,
							it
						)
					}
				}
			)
		}
	}
}

@Composable
private fun SwitchBox(
	raw: String,
	contentType: String,
	format: Int?,
	qrImage: Bitmap?,
	selected: MutableState<Boolean>,
	onCaptureReady: (suspend () -> Bitmap?) -> Unit
) {
	val graphicsLayer = rememberGraphicsLayer()
	val qrImageVCard = remember { mutableStateOf<Bitmap?>(null) }
	val isVirtualCard = contentType == "Virtual Card"
	val vCardData = remember(raw) {
		raw.toVCardData()
	}
	
	LaunchedEffect(graphicsLayer) {
		onCaptureReady {
			graphicsLayer.toImageBitmap().asAndroidBitmap()
		}
	}
	
	LaunchedEffect(selected.value, raw, format) {
		if (selected.value.not()) return@LaunchedEffect
		if (qrImageVCard.value != null) return@LaunchedEffect
		
		QrGenerator.generate(
			text = raw,
			format = format,
			size = Int.FIVE_HUNDRED_TWELVE,
			colors = listOf("#FFFFFF", "#3A3A3A"),
			onType = {},
			onCapture = { qrImageVCard.value = it }
		)
	}
	
	Box(contentAlignment = Alignment.Center) {
		AnimatedContent(
			targetState = selected.value,
			label = "box_animation"
		) { isSelected ->
			SwitchBoxContent(
				isSelected = isSelected,
				isVirtualCard = isVirtualCard,
				data = vCardData,
				qrBitmap = qrImage,
				qrBitmapVCard = qrImageVCard.value,
				graphicsLayer = graphicsLayer,
				onToggle = {
					if (isVirtualCard) selected.value = selected.value.not()
				}
			)
		}
	}
}

@Composable
private fun SwitchBoxContent(
	isSelected: Boolean,
	isVirtualCard: Boolean,
	data: VCardData,
	qrBitmap: Bitmap?,
	qrBitmapVCard: Bitmap?,
	graphicsLayer: GraphicsLayer,
	onToggle: () -> Unit
) {
	if (isSelected && isVirtualCard) {
		qrBitmapVCard?.let { bitmap ->
			Box {
				VCardSegmented(
					modifier = Modifier
						.alpha(0f)
						.drawWithContent {
							graphicsLayer.record {
								this@drawWithContent.drawContent()
							}
							
							drawLayer(graphicsLayer)
						},
					data = data,
					qrBitmap = bitmap.asImageBitmap(),
					rounded = false,
					clickable = false
				)
				
				VCardSegmented(
					data = data,
					qrBitmap = bitmap.asImageBitmap(),
					rounded = true,
					onClick = onToggle
				)
			}
		}
	} else {
		qrBitmap?.let { bitmap ->
			QrBoxContent(
				qrBitmap = bitmap,
				enabled = isVirtualCard,
				onClick = onToggle
			)
		}
	}
}

@Composable
private fun QrBoxContent(
	qrBitmap: Bitmap,
	enabled: Boolean,
	onClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.height(SeDimen.Dp180)
			.clip(RoundedCornerShape(SeDimen.Dp15))
			.background(Color.White)
			.clickable(enabled = enabled) { onClick() }
			.padding(SeDimen.Dp12),
		contentAlignment = Alignment.Center
	) {
		Image(
			bitmap = qrBitmap.asImageBitmap(),
			contentDescription = null,
			modifier = Modifier.fillMaxWidth()
		)
	}
}

@Composable
private fun VCardSegmented(
	modifier: Modifier = Modifier,
	data: VCardData,
	qrBitmap: ImageBitmap,
	rounded: Boolean = true,
	clickable: Boolean = true,
	onClick: () -> Unit = {},
) {
	val shape = if (rounded) {
		RoundedCornerShape(SeDimen.Dp14)
	} else {
		RectangleShape
	}
	
	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(SeDimen.Dp200)
			.clip(shape)
			.background(Color(0xFFF3F3F3))
			.clickable(enabled = clickable) {
				onClick()
			}
	) {
		TopRightDecoration()
		
		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(SeDimen.Dp16),
			horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp16),
			verticalAlignment = Alignment.Bottom
		) {
			Column(
				modifier = Modifier.weight(Float.ONE)
			) {
				with(data) {
					LeftProfileContent(
						name,
						company,
						position,
						numberPhone,
						email,
						address
					)
				}
			}
			
			Image(
				bitmap = qrBitmap,
				contentDescription = null,
				modifier = Modifier.size(SeDimen.Dp60)
			)
		}
	}
}

@Composable
private fun TopRightDecoration() {
	Canvas(
		modifier = Modifier
			.fillMaxWidth()
			.height(SeDimen.Dp120)
	) {
		drawCircle(
			color = charcoalGray,
			radius = size.width * Float.ZERO_POINT_TWENTY_FOUR,
			center = Offset(
				size.width,
				Float.ZERO
			),
			style = Stroke(width = Float.FIFTY_SIX)
		)
		
		drawCircle(
			color = charcoalGray,
			radius = size.width * Float.ZERO_POINT_EIGHTEEN,
			center = Offset(
				size.width,
				Float.ZERO
			),
			style = Stroke(width = Float.TWENTY_EIGHT)
		)
	}
}

@Composable
private fun LeftProfileContent(
	name: String,
	company: String,
	position: String,
	numberPhone: String,
	email: String,
	address: String,
) {
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier.fillMaxHeight()
	) {
		Column {
			Text(
				text = name,
				style = typography.bodyLarge.copy(fontWeight = FontWeight.W600),
				color = darkCharcoal
			)
			
			Text(
				text = "$company - $position",
				style = typography.labelMedium,
				color = dimGray
			)
		}
		
		Column(
			verticalArrangement = Arrangement.spacedBy(SeDimen.Dp8)
		) {
			ContactItem(
				icon = Icons.Default.Phone,
				text = numberPhone
			)
			
			ContactItem(
				icon = Icons.Default.Email,
				text = email
			)
			
			ContactItem(
				icon = Icons.Default.LocationOn,
				text = address
			)
		}
	}
}

@Composable
private fun ContactItem(
	icon: ImageVector,
	text: String
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp8)
	) {
		Box(
			modifier = Modifier
				.size(SeDimen.Dp24)
				.background(
					charcoalGray,
					CircleShape
				),
			contentAlignment = Alignment.Center
		) {
			Icon(
				imageVector = icon,
				contentDescription = null,
				tint = Color.White,
				modifier = Modifier.size(SeDimen.Dp14)
			)
		}
		
		Text(
			text = text,
			style = typography.labelSmall,
			color = charcoalGray
		)
	}
}

@Composable
private fun ButtonSegmented(
	onSave: () -> Unit,
	onShare: () -> Unit
) {
	val actions = listOf(
		ButtonAction(
			"Save",
			Icons.Default.Save,
			lightGreen,
			onSave
		),
		ButtonAction(
			"Share",
			Icons.Default.Share,
			peach,
			onShare
		)
	)
	
	Row(
		horizontalArrangement = Arrangement.spacedBy(SeDimen.Dp16)
	) {
		actions.forEach { action ->
			Button(
				onClick = action.onClick,
				modifier = Modifier.weight(Float.ONE),
				colors = ButtonDefaults.buttonColors(
					containerColor = action.color
				),
				shape = RoundedCornerShape(SeDimen.Dp10)
			) {
				Icon(
					imageVector = action.icon,
					contentDescription = null,
					tint = colorScheme.onSurface
				)
				
				Spacer(Modifier.width(SeDimen.Dp8))
				
				Text(
					text = action.title,
					color = colorScheme.onSurface
				)
			}
		}
	}
}