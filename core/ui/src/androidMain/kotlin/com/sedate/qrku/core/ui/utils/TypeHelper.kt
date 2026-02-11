package com.sedate.qrku.core.ui.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.google.zxing.client.result.AddressBookParsedResult
import com.google.zxing.client.result.CalendarParsedResult
import com.google.zxing.client.result.EmailAddressParsedResult
import com.google.zxing.client.result.GeoParsedResult
import com.google.zxing.client.result.ISBNParsedResult
import com.google.zxing.client.result.ParsedResultType
import com.google.zxing.client.result.ProductParsedResult
import com.google.zxing.client.result.ResultParser
import com.google.zxing.client.result.SMSParsedResult
import com.google.zxing.client.result.TextParsedResult
import com.google.zxing.client.result.URIParsedResult
import com.google.zxing.client.result.VINParsedResult
import com.google.zxing.client.result.WifiParsedResult

fun parseScanResult(
	value: String,
	format: BarcodeFormat
): ParsedContent {
	val result = Result(
		value,
		null,
		null,
		format
	)
	val parsed = ResultParser.parseResult(result)

	return when (parsed.type) {
		ParsedResultType.URI -> {
			val r = parsed as URIParsedResult
			ParsedContent.Url(r.uri)
		}

		ParsedResultType.TEXT -> {
			val r = parsed as TextParsedResult
			ParsedContent.Text(r.text)
		}

		ParsedResultType.PRODUCT -> {
			val r = parsed as ProductParsedResult
			ParsedContent.Product(r.productID)
		}

		ParsedResultType.WIFI -> {
			val r = parsed as WifiParsedResult
			ParsedContent.Wifi(
				ssid = r.ssid,
				password = r.password,
				encryption = r.networkEncryption
			)
		}

		ParsedResultType.ADDRESSBOOK -> {
			val r = parsed as AddressBookParsedResult
			ParsedContent.Contact(
				name = r.names?.firstOrNull()
			)
		}

		ParsedResultType.EMAIL_ADDRESS -> {
			val r = parsed as EmailAddressParsedResult
			ParsedContent.Email(
				email = r.tos?.firstOrNull()
			)
		}

		ParsedResultType.SMS -> {
			val r = parsed as SMSParsedResult
			ParsedContent.Sms(
				number = r.numbers?.firstOrNull(),
				message = r.body
			)
		}

		ParsedResultType.GEO -> {
			val r = parsed as GeoParsedResult
			ParsedContent.Geo(
				lat = r.latitude,
				lng = r.longitude
			)
		}

		ParsedResultType.CALENDAR -> {
			val r = parsed as CalendarParsedResult
			ParsedContent.Calendar(
				title = r.summary,
				description = r.description
			)
		}

		ParsedResultType.ISBN -> {
			val r = parsed as ISBNParsedResult
			ParsedContent.ISBN(
				isbn = r.isbn
			)
		}

		ParsedResultType.VIN -> {
			val r = parsed as VINParsedResult
			ParsedContent.VIN(
				vin = r.vin
			)
		}

		else -> {
			ParsedContent.Text(parsed.displayResult)
		}
	}
}

sealed class ParsedContent {
	data class Url(val url: String) : ParsedContent()
	data class Text(val text: String) : ParsedContent()
	data class Product(val code: String) : ParsedContent()
	data class Wifi(
		val ssid: String,
		val password: String?,
		val encryption: String
	) : ParsedContent()

	data class Contact(val name: String?) : ParsedContent()
	data class Email(val email: String?) : ParsedContent()
	data class Sms(
		val number: String?,
		val message: String?
	) : ParsedContent()

	data class Geo(
		val lat: Double,
		val lng: Double
	) : ParsedContent()

	data class Calendar(
		val title: String?,
		val description: String?,
	) : ParsedContent()

	data class ISBN(
		val isbn: String?
	) : ParsedContent()

	data class VIN(
		val vin: String?
	) : ParsedContent()
}
