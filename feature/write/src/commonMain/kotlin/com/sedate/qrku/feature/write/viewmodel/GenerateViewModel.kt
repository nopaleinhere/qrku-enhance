package com.sedate.qrku.feature.write.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import com.sedate.qrku.feature.write.data.GenerateContent
import com.sedate.qrku.feature.write.data.GenerateEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Stable
class GenerateViewModel : ViewModel() {
	private val generateEngine = GenerateEngine()
	private val _result = MutableStateFlow<Pair<String, Int>?>(null)
	val result = _result.asStateFlow()

	fun generate(content: GenerateContent) {
		_result.value = generateEngine.execute(content)
	}
}

@Stable
class FormState(
	val values: SnapshotStateMap<String, String> = mutableStateMapOf(),
	val errors: SnapshotStateMap<String, String?> = mutableStateMapOf(),
	val touched: SnapshotStateMap<String, Boolean> = mutableStateMapOf()
)