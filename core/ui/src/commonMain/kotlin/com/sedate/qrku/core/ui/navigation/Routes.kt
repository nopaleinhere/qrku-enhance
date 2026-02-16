package com.sedate.qrku.core.ui.navigation

import androidx.compose.runtime.Stable
import com.sedate.qrku.core.common.constants.BarcodeType
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

sealed interface Routes

@Serializable
data object WriteRoute : Routes {
	@Serializable
	data object Landing : Routes

	@Serializable
	data class Generate(
		val type: String,
		val subType: BarcodeType.Support,
	) : Routes
}

@Serializable
data object ScanRoute : Routes {
	@Serializable
	data object Scan : Routes
}

@Serializable
data object OverviewRoute : Routes {
	@Serializable
	data class QrCapture(
		val actionType: BarcodeType.Action,
		val type: String,
		val input: String,
		val format: Int?,
		val date: Long? = null
	) : Routes
}

@Serializable
data object HistoryRoute : Routes {
	@Serializable
	data object History : Routes
}

@Serializable
data object SettingsRoute : Routes {
	@Serializable
	data object Settings : Routes

	@Serializable
	data object About : Routes
}

@Stable
data class NavEntry @OptIn(ExperimentalUuidApi::class) constructor(
	val id: String = Uuid.random()
		.toString(),
	val route: Routes
)