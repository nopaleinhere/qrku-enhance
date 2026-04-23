package com.sedate.qrku.feature.write.data

import androidx.compose.ui.text.input.KeyboardType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.utils.isValidDateFormat

sealed class FormPart {

    abstract val key: String
    abstract val required: Boolean

    abstract fun validate(value: Any?): String?

    // 🔹 TEXT FIELD
    data class TextField(
        override val key: String,
        val label: String,
        override val required: Boolean = true,
        val keyboardType: KeyboardType = KeyboardType.Text,
        val maxLength: Int? = null,
        val minLines: Int = Int.ONE,
        val maxLines: Int = Int.ONE,
        val description: String? = null,
        val validator: (String) -> String? = { null }
    ) : FormPart() {
        override fun validate(value: Any?): String? {
            val text = value as? String ?: String.EMPTY

            return when {
                required && text.isEmpty() -> "Required"
                maxLength != null && text.length > maxLength -> "Max $maxLength characters"
                else -> validator(text)
            }
        }
    }

    data class Dropdown(
        override val key: String,
        val label: String,
        val options: List<String>,
        override val required: Boolean = true
    ) : FormPart() {
        override fun validate(value: Any?): String? {
            val text = value as? String ?: String.EMPTY

            return if (required && text.isEmpty()) "Required" else null
        }
    }

    data class DateTimeField(
        override val key: String,
        val label: String,
        override val required: Boolean = true
    ) : FormPart() {
        override fun validate(value: Any?): String? {
            val text = value as? String ?: String.EMPTY

            return when {
                required && text.isEmpty() -> "Required"
                text.isNotEmpty() && isValidDateFormat(text).not() -> "Invalid date"
                else -> null
            }
        }
    }

    data class Checkbox(
        override val key: String,
        val label: String,
        override val required: Boolean = false,
        val checkedValue: String = String.EMPTY
    ) : FormPart() {
        override fun validate(value: Any?): String? {
            val checked = value as? Boolean ?: false

            return if (required && !checked) "Must be checked" else null
        }
    }
}