package com.sedate.qrku.core.ui.material.dropdown

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import com.sedate.qrku.core.common.constants.SeConst.ZERO_POINT_FIVE
import com.sedate.qrku.core.ui.utils.SeDimen

@Composable
fun SeDropdown(
    expanded: Boolean,
    onDismiss: () -> Unit,
    options: List<String>,
    selected: String?,
    onSelect: (String) -> Unit
) {
    BoxWithConstraints {
        val width = maxWidth

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            modifier = Modifier.width(width * Float.ZERO_POINT_FIVE),
            offset = DpOffset(SeDimen.Dp0, SeDimen.Dp60),
            shape = RoundedCornerShape(SeDimen.Dp12),
            tonalElevation = SeDimen.Dp6
        ) {
            options.forEach { option ->
                val isSelected = option == selected

                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontWeight = if (isSelected)
                                FontWeight.SemiBold
                            else
                                FontWeight.Normal
                        )
                    },
                    trailingIcon = if (isSelected) {
                        {
                            Icon(Icons.Default.Check, null)
                        }
                    } else null,
                    onClick = {
                        onSelect(option)
                        onDismiss()
                    }
                )
            }
        }
    }
}