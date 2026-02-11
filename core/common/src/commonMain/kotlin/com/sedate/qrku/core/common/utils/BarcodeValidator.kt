package com.sedate.qrku.core.common.utils

import com.sedate.qrku.core.common.constants.BarcodeFormat
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst
import com.sedate.qrku.core.common.constants.SeConst.ELEVEN
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.TEN
import com.sedate.qrku.core.common.constants.SeConst.THREE
import com.sedate.qrku.core.common.constants.SeConst.TWELVE
import com.sedate.qrku.core.common.constants.SeConst.TWO
import com.sedate.qrku.core.common.constants.SeConst.ZERO

object BarcodeValidator {

	fun isValid(
		input: String,
		format: BarcodeType.Support
	): Boolean {
		return when (format) {
			BarcodeType.Support.TEXT ->
				input.isNotBlank()

			BarcodeType.Support.LINK ->
				input.isNotBlank()

			BarcodeType.Support.UPC_A ->
				isValidUpcA(input)

			else -> false
		}
	}

	fun isNotEmpty(text: String): Boolean =
		text.isNotBlank()

	private fun isValidUrl(text: String): Boolean {
		if (text.isBlank()) return false

		val url = text.trim()

		return url.startsWith(
			SeConst.HTTP,
			true
		) ||
				url.startsWith(
					SeConst.HTTPS,
					true
				)
	}

	private fun isValidUpcA(code: String): Boolean {
		if (code.length != Int.TWELVE) return false
		if (code.all(Char::isDigit)
				.not()
		) return false

		val digits = code.map { it - Char.ZERO }

		val sumOdd = digits
			.take(Int.ELEVEN)
			.filterIndexed { index, _ -> index % Int.TWO == Int.ZERO }
			.sum() * Int.THREE

		val sumEven = digits
			.take(Int.ELEVEN)
			.filterIndexed { index, _ -> index % Int.TWO == Int.ONE }
			.sum()

		val checksum = (Int.TEN - ((sumOdd + sumEven) % Int.TEN)) % Int.TEN

		return checksum == digits.last()
	}
}