package com.sedate.qrku.feature.write.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import com.sedate.qrku.feature.write.data.FormPart

@Stable
class GenerateViewModel : ViewModel() {
    fun validateForm(
        fields: List<FormPart>,
        state: FormState
    ): Boolean {
        var isValid = true

        fields.forEach { field ->
            val error = field.validate(state.values[field.key])
            state.errors[field.key] = error
            if (error != null) isValid = false
        }

        return isValid
    }
}

@Stable
class FormState(
    val values: SnapshotStateMap<String, Any> = mutableStateMapOf(),
    val errors: SnapshotStateMap<String, String?> = mutableStateMapOf(),
    val touched: SnapshotStateMap<String, Boolean> = mutableStateMapOf()
)