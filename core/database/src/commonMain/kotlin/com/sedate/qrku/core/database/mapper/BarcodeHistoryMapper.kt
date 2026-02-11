package com.sedate.qrku.core.database.mapper

import com.sedate.qrku.core.database.entity.BarcodeHistoryEntity
import com.sedate.qrku.core.model.BarcodeHistory

fun BarcodeHistoryEntity.toModel(): BarcodeHistory =
	BarcodeHistory(
		id = id,
		actionType = actionType,
		result = result,
		contentType = contentType,
		format = format,
		formatCode = formatCode,
		createdAt = createdAt
	)