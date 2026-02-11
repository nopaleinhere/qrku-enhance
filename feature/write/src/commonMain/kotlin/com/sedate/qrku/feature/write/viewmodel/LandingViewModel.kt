package com.sedate.qrku.feature.write.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.feature.write.data.GenerateType
import com.sedate.qrku.feature.write.data.IconKey
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.desc_barcode
import com.sedate.qrku.resources.empty
import com.sedate.qrku.resources.ic_barcode
import com.sedate.qrku.resources.ic_link
import com.sedate.qrku.resources.ic_text
import org.jetbrains.compose.resources.DrawableResource

@Stable
class LandingViewModel : ViewModel() {
	private val categoryList = listOf(
		GenerateType(
			"QR Code",
			BarcodeType.Support.TEXT,
			Res.string.empty,
			iconKey = IconKey.TEXT
		),
		GenerateType(
			"QR Code",
			BarcodeType.Support.LINK,
			Res.string.empty,
			iconKey = IconKey.LINK
		),
		GenerateType(
			"Barcode",
			BarcodeType.Support.UPC_A,
			Res.string.desc_barcode,
			iconKey = IconKey.BARCODE
		)
	)

	val groupedData: Map<String, List<GenerateType<BarcodeType.Support>>> =
		categoryList.groupBy { it.type }

	fun IconKey.toDrawable(): DrawableResource? =
		when (this) {
			IconKey.TEXT -> Res.drawable.ic_text
			IconKey.LINK -> Res.drawable.ic_link
			IconKey.BARCODE -> Res.drawable.ic_barcode
			else -> null
		}
}