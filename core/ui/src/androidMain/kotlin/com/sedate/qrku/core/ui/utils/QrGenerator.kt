package com.sedate.qrku.core.ui.utils

import android.graphics.Bitmap
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.core.graphics.toColorInt
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.sedate.qrku.core.common.constants.BarcodeFormat
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.google.zxing.BarcodeFormat as ZFormat

object QrGenerator {
	fun generate(
		format: Int?,
		text: String,
		size: Int,
		colors: List<String> = listOf("#3A3A3A", "#FFFFFF"),
		onType: (ParsedContent) -> Unit,
		onCapture: (Bitmap?) -> Unit
	) {
		val zxingFormat = supportFormat(format)
		val color = colors.take(Int.TWO)
		val foreground = color.first().toColorInt()
		val background = color.last().toColorInt()
		
		try {
			val hints = mapOf(
				EncodeHintType.CHARACTER_SET to SeConst.UTF_8,
				EncodeHintType.MARGIN to Int.ONE
			)
			
			val (width, height) = if (zxingFormat == ZFormat.QR_CODE) {
				size to size
			} else {
				size * Int.TWO to size / Int.TWO
			}
			val matrix = MultiFormatWriter().encode(
				text,
				zxingFormat,
				width,
				height,
				hints
			)
			val bitmap = createBitmap(
				width,
				height
			)
			
			for (x in Int.ZERO until width) {
				for (y in Int.ZERO until height) {
					bitmap[x, y] = if (matrix[x, y]) foreground else background
				}
			}
			
			onCapture(bitmap)
			
			zxingFormat?.let {
				val type = parseScanResult(
					text,
					it
				)
				
				onType.invoke(type)
			}
		} catch (_: Exception) {
			onCapture(null)
		}
	}
}

private fun supportFormat(format: Int?): ZFormat? {
	return when (format) {
		BarcodeType.Support.TEXT.format, BarcodeType.Support.PLAY_STORE.format, BarcodeType.Support.CALENDAR.format, BarcodeType.Support.CONTACT.format, BarcodeType.Support.EMAIL.format, BarcodeType.Support.PHONE.format, BarcodeType.Support.VCARD.format, BarcodeType.Support.WIFI.format, BarcodeType.Support.MESSAGE.format, BarcodeType.Support.LINK.format -> ZFormat.QR_CODE
		BarcodeFormat.FORMAT_UPC_A -> ZFormat.UPC_A
		BarcodeFormat.FORMAT_CODE_128 -> ZFormat.CODE_128
		BarcodeFormat.FORMAT_CODE_39 -> ZFormat.CODE_39
		BarcodeFormat.FORMAT_CODE_93 -> ZFormat.CODE_93
		BarcodeFormat.FORMAT_CODABAR -> ZFormat.CODABAR
		BarcodeFormat.FORMAT_DATA_MATRIX -> ZFormat.DATA_MATRIX
		BarcodeFormat.FORMAT_EAN_13 -> ZFormat.EAN_13
		BarcodeFormat.FORMAT_EAN_8 -> ZFormat.EAN_8
		BarcodeFormat.FORMAT_ITF -> ZFormat.ITF
		BarcodeFormat.FORMAT_PDF417 -> ZFormat.PDF_417
		BarcodeFormat.FORMAT_AZTEC -> ZFormat.AZTEC
		BarcodeFormat.FORMAT_UPC_E -> ZFormat.UPC_E
		else -> null
	}
}

fun typeFormat(format: Int?): String {
	return when (format) {
		BarcodeType.Support.TEXT.format, BarcodeType.Support.PLAY_STORE.format, BarcodeType.Support.CALENDAR.format, BarcodeType.Support.CONTACT.format, BarcodeType.Support.EMAIL.format, BarcodeType.Support.PHONE.format, BarcodeType.Support.VCARD.format, BarcodeType.Support.WIFI.format, BarcodeType.Support.MESSAGE.format, BarcodeType.Support.LINK.format -> "QR Code"
		BarcodeFormat.FORMAT_UPC_A -> "UPC_A"
		BarcodeFormat.FORMAT_CODE_128 -> "CODE_128"
		BarcodeFormat.FORMAT_CODE_39 -> "CODE_39"
		BarcodeFormat.FORMAT_CODE_93 -> "CODE_93"
		BarcodeFormat.FORMAT_CODABAR -> "CODABAR"
		BarcodeFormat.FORMAT_DATA_MATRIX -> "DATA_MATRIX"
		BarcodeFormat.FORMAT_EAN_13 -> "EAN_13"
		BarcodeFormat.FORMAT_EAN_8 -> "EAN_8"
		BarcodeFormat.FORMAT_ITF -> "ITF"
		BarcodeFormat.FORMAT_PDF417 -> "PDF_417"
		BarcodeFormat.FORMAT_AZTEC -> "AZTEC"
		BarcodeFormat.FORMAT_UPC_E -> "UPC_E"
		else -> String.EMPTY
	}
}