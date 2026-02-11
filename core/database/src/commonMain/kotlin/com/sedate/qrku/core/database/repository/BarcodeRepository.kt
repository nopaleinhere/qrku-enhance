package com.sedate.qrku.core.database.repository

import com.sedate.qrku.core.model.BarcodeHistory

interface BarcodeRepository {
	suspend fun saveScan(
		result: String,
		contentType: String,
		format: String,
		formatCode: Int?
	)

	suspend fun saveCreate(
		content: String,
		contentType: String,
		format: String,
		formatCode: Int?
	)

	suspend fun getHistory(): List<BarcodeHistory>

	suspend fun deleteByIds(ids: List<Long>)
}