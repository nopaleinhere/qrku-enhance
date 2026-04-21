package com.sedate.qrku.feature.write.data

import androidx.compose.ui.text.input.KeyboardType
import com.sedate.qrku.core.common.constants.BarcodeType

object FormSchemaRegistry {
	private val schemas: Map<BarcodeType.Support, List<FormPart>> = mapOf(
		BarcodeType.Support.CONTACT to contactSchema,
		BarcodeType.Support.PHONE to phoneSchema,
		BarcodeType.Support.EMAIL to emailSchema,
		BarcodeType.Support.MESSAGE to smsSchema,
		BarcodeType.Support.TEXT to textSchema,
		BarcodeType.Support.LINK to linkSchema,
		BarcodeType.Support.WIFI to wifiSchema,
		BarcodeType.Support.CALENDAR to calendarSchema,
		BarcodeType.Support.VCARD to vcardSchema,
		BarcodeType.Support.PLAY_STORE to playStoreSchema,
		BarcodeType.Support.UPC_A to barcodeSchema
	)

	fun get(type: BarcodeType.Support): List<FormPart> {
		return schemas[type] ?: emptyList()
	}
}

private val contactSchema = listOf(
	FormPart.TextField(
		key = "name",
		label = "Name",
		description = "Enter full name"
	),
	FormPart.TextField(
		key = "phone",
		label = "Phone",
		keyboardType = KeyboardType.Phone,
		description = "Enter phone number",
		validator = {
			if (it.isEmpty()) "Phone required"
			else null
		}
	),
	FormPart.TextField(
		key = "email",
		label = "Email",
		keyboardType = KeyboardType.Email,
		required = false,
		description = "Optional email"
	)
)

private val phoneSchema = listOf(
	FormPart.TextField(
		key = "phone",
		label = "Phone Number",
		keyboardType = KeyboardType.Phone,
		description = "Enter phone number",
		validator = {
			if (it.isEmpty()) "Phone required"
			else null
		}
	)
)

private val emailSchema = listOf(
	FormPart.TextField(
		key = "email",
		label = "Email",
		keyboardType = KeyboardType.Email,
		description = "Enter email address",
		validator = {
			when {
				it.isEmpty() -> "Email required"
				!it.contains("@") -> "Invalid email"
				else -> null
			}
		}
	),
	FormPart.TextField(
		key = "subject",
		label = "Subject",
		required = false
	),
	FormPart.TextField(
		key = "body",
		label = "Message",
		required = false
	)
)

private val smsSchema = listOf(
	FormPart.TextField(
		key = "phone",
		label = "Phone Number",
		keyboardType = KeyboardType.Phone,
		description = "Receiver number",
		validator = {
			if (it.isEmpty()) "Phone required"
			else null
		}
	),
	FormPart.TextField(
		key = "message",
		label = "Message",
		required = false,
		description = "Optional message"
	)
)

private val textSchema = listOf(
	FormPart.TextField(
		key = "text",
		label = "Text",
		description = "Enter any text",
		maxLength = Int.MAX_VALUE
	)
)

private val linkSchema = listOf(
	FormPart.Dropdown(
		key = "protocol",
		label = "Protocol",
		options = listOf("https://", "http://")
	),
	FormPart.TextField(
		key = "url",
		label = "Website",
		description = "Enter domain (e.g. google.com)",
		validator = {
			when {
				it.isEmpty() -> "URL required"
				it.contains(" ") -> "Invalid URL"
				else -> null
			}
		}
	)
)

private val wifiSchema = listOf(
	FormPart.TextField(
		key = "ssid",
		label = "SSID",
		description = "WiFi name",
		validator = {
			if (it.isEmpty()) "SSID required"
			else null
		}
	),
	FormPart.TextField(
		key = "password",
		label = "Password",
		required = false,
		description = "Leave empty if open network"
	),
	FormPart.Dropdown(
		key = "security",
		label = "Security",
		options = listOf("WPA", "WEP", "None")
	)
)

private val calendarSchema = listOf(
	FormPart.TextField(
		key = "title",
		label = "Event Title",
		description = "Enter event name"
	),
	FormPart.TextField(
		key = "location",
		label = "Location",
		required = false
	),
	FormPart.DateTimeField(
		key = "start",
		label = "Start Date"
	),
	FormPart.DateTimeField(
		key = "end",
		label = "End Date"
	)
)

private val vcardSchema = listOf(
	FormPart.TextField(
		key = "name",
		label = "Name"
	),
	FormPart.TextField(
		key = "phone",
		label = "Phone",
		keyboardType = KeyboardType.Phone
	),
	FormPart.TextField(
		key = "email",
		label = "Email",
		keyboardType = KeyboardType.Email,
		required = false
	),
	FormPart.TextField(
		key = "company",
		label = "Company",
		required = false
	),
	FormPart.TextField(
		key = "address",
		label = "Address",
		required = false
	)
)

private val playStoreSchema = listOf(
	FormPart.TextField(
		key = "package",
		label = "Package Name",
		description = "e.g. com.whatsapp",
		validator = {
			when {
				it.isEmpty() -> "Package name required"
				it.contains(".").not() -> "Invalid package name"
				else -> null
			}
		}
	)
)

private val barcodeSchema = listOf(
	FormPart.TextField(
		key = "barcode",
		label = "UPC-A Code",
		keyboardType = KeyboardType.Number,
		maxLength = 12,
		description = "Must be exactly 12 digits",
		validator = {
			when {
				it.length != 12 -> "Must be 12 digits"
				else -> null
			}
		}
	)
)