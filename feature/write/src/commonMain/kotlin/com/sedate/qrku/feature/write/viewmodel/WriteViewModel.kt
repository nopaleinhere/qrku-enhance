package com.sedate.qrku.feature.write.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.model.IconKey
import com.sedate.qrku.feature.write.data.GenerateType
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.barcode_ic
import com.sedate.qrku.resources.calendar_ic
import com.sedate.qrku.resources.contact_ic
import com.sedate.qrku.resources.identity_ic
import com.sedate.qrku.resources.link_ic
import com.sedate.qrku.resources.mail_ic
import com.sedate.qrku.resources.phone_ic
import com.sedate.qrku.resources.playstore_ic
import com.sedate.qrku.resources.sms_ic
import com.sedate.qrku.resources.text_ic
import com.sedate.qrku.resources.wifi_ic
import org.jetbrains.compose.resources.DrawableResource

@Stable
class WriteViewModel : ViewModel() {
	private val categoryList = listOf(
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.CONTACT,
			IconKey.CONTACT
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.PHONE,
			IconKey.PHONE
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.EMAIL,
			IconKey.EMAIL
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.MESSAGE,
			IconKey.MESSAGE
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.TEXT,
			IconKey.TEXT
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.LINK,
			IconKey.LINK
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.WIFI,
			IconKey.WIFI
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.CALENDAR,
			IconKey.CALENDAR
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.VCARD,
			IconKey.VCARD
		),
		GenerateType(
			BarcodeType.Category.QR,
			BarcodeType.Support.PLAY_STORE,
			IconKey.PLAY_STORE
		),
		GenerateType(
			BarcodeType.Category.BARCODE,
			BarcodeType.Support.UPC_A,
			IconKey.BARCODE
		)
	)

	val groupedData: Map<BarcodeType.Category, List<GenerateType>> =
		categoryList.groupBy { it.category }

	fun getByCategory(category: BarcodeType.Category): List<GenerateType> {
		return groupedData[category].orEmpty()
	}

	fun IconKey.toDrawable(): DrawableResource? =
		when (this) {
			IconKey.CONTACT -> Res.drawable.contact_ic
			IconKey.PHONE -> Res.drawable.phone_ic
			IconKey.EMAIL -> Res.drawable.mail_ic
			IconKey.MESSAGE -> Res.drawable.sms_ic
			IconKey.TEXT -> Res.drawable.text_ic
			IconKey.LINK -> Res.drawable.link_ic
			IconKey.WIFI -> Res.drawable.wifi_ic
			IconKey.CALENDAR -> Res.drawable.calendar_ic
			IconKey.VCARD -> Res.drawable.identity_ic
			IconKey.PLAY_STORE -> Res.drawable.playstore_ic
			IconKey.BARCODE -> Res.drawable.barcode_ic
			else -> null
		}
}