package com.sedate.qrku.feature.write.data

import com.sedate.qrku.core.common.constants.BarcodeFormat
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.SPACE
import com.sedate.qrku.feature.write.viewmodel.FormState

class GenerateEngine {
	fun execute(content: GenerateContent): Pair<String, Int> {
		val encoded = CodeEncoder.encode(content)

		val format = when (content) {
			is GenerateContent.Barcode -> content.format
			else -> BarcodeFormat.FORMAT_QR_CODE
		}

		return encoded to format
	}
}

object CodeEncoder {
	fun encode(content: GenerateContent): String {
		return when (content) {
			is GenerateContent.Contact -> buildString {
				append("MECARD:")
				append("N:${content.name};")
				append("TEL:${content.phone};")

				if (content.email.isNotEmpty()) {
					append("EMAIL:${content.email};")
				}

				append(";")
			}

			is GenerateContent.Phone ->
				"tel:${content.number}"

			is GenerateContent.Email ->
				"mailto:${content.email}" +
						"?subject=${encodeUrl(content.subject)}" +
						"&body=${encodeUrl(content.body)}"

			is GenerateContent.Sms ->
				"SMSTO:${content.number}:${content.message}"

			is GenerateContent.Text ->
				content.text

			is GenerateContent.Link ->
				content.url

			is GenerateContent.Wifi -> {
				val security = if (content.security == "None") String.EMPTY else content.security

				"WIFI:T:$security;S:${content.ssid};P:${content.password};;"
			}

			is GenerateContent.Calendar ->
				"""
                BEGIN:VEVENT
                SUMMARY:${content.title}
                LOCATION:${content.location}
                DTSTART:${content.start}
                DTEND:${content.end}
                END:VEVENT
                """.trimIndent()

			is GenerateContent.VCard ->
				"""
                BEGIN:VCARD
                VERSION:3.0
                N:${content.name}
                TEL:${content.phone}
                EMAIL:${content.email}
                ORG:${content.company}
                ADR:${content.address}
                END:VCARD
                """.trimIndent()

			is GenerateContent.PlayStore ->
				"https://play.google.com/store/apps/details?id=${content.packageName}"

			is GenerateContent.Barcode ->
				content.value
		}
	}

	private fun encodeUrl(value: String): String {
		return value.replace(
			String.SPACE,
			"%20"
		)
	}
}

fun validateForm(
	fields: List<FormPart>,
	state: FormState
): Boolean {
	var isValid = true

	fields.forEach { field ->
		when (field) {
			is FormPart.TextField -> {
				val value = state.values[field.key].orEmpty()

				val error = when {
					field.required && value.isEmpty() -> "Required"
					else -> field.validator(value)
				}

				state.errors[field.key] = error

				if (error != null) isValid = false
			}

			is FormPart.Dropdown -> {
				val value = state.values[field.key].orEmpty()

				if (field.required && value.isEmpty()) {
					state.errors[field.key] = "Required"
					isValid = false
				}
			}

			is FormPart.DateTimeField -> {
				val value = state.values[field.key].orEmpty()

				if (field.required && value.isEmpty()) {
					state.errors[field.key] = "Required"
					isValid = false
				}
			}
		}
	}

	return isValid
}

fun mapToContent(
	type: BarcodeType.Support,
	values: Map<String, String>
): GenerateContent {
	return when (type) {
		BarcodeType.Support.CONTACT ->
			GenerateContent.Contact(
				name = values["name"].orEmpty(),
				phone = values["phone"].orEmpty()
			)

		BarcodeType.Support.PHONE ->
			GenerateContent.Phone(
				number = values["phone"].orEmpty()
			)

		BarcodeType.Support.EMAIL ->
			GenerateContent.Email(
				email = values["email"].orEmpty(),
				subject = values["subject"].orEmpty(),
				body = values["body"].orEmpty()
			)

		BarcodeType.Support.MESSAGE ->
			GenerateContent.Sms(
				number = values["phone"].orEmpty(),
				message = values["message"].orEmpty()
			)

		// 🔹 TEXT
		BarcodeType.Support.TEXT ->
			GenerateContent.Text(
				text = values["text"].orEmpty()
			)

		BarcodeType.Support.LINK ->
			GenerateContent.Link(
				url = (values["protocol"] ?: "https://") + values["url"].orEmpty()
			)

		BarcodeType.Support.WIFI ->
			GenerateContent.Wifi(
				ssid = values["ssid"].orEmpty(),
				password = values["password"].orEmpty(),
				security = values["security"].orEmpty()
			)

		BarcodeType.Support.CALENDAR ->
			GenerateContent.Calendar(
				title = values["title"].orEmpty(),
				location = values["location"].orEmpty(),
				start = values["start"].orEmpty(),
				end = values["end"].orEmpty()
			)

		BarcodeType.Support.VCARD ->
			GenerateContent.VCard(
				name = values["name"].orEmpty(),
				phone = values["phone"].orEmpty(),
				email = values["email"].orEmpty(),
				company = values["company"].orEmpty(),
				address = values["address"].orEmpty()
			)

		BarcodeType.Support.PLAY_STORE ->
			GenerateContent.PlayStore(
				packageName = values["package"].orEmpty()
			)

		BarcodeType.Support.UPC_A ->
			GenerateContent.Barcode(
				value = values["barcode"].orEmpty(),
				format = BarcodeFormat.FORMAT_UPC_A
			)

		else ->
			GenerateContent.Text(values["value"].orEmpty())
	}
}
