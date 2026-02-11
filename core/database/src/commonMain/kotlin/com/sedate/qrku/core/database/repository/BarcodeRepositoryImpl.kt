package com.sedate.qrku.core.database.repository

import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.database.dao.BarcodeHistoryDao
import com.sedate.qrku.core.database.entity.BarcodeHistoryEntity
import com.sedate.qrku.core.database.mapper.toModel
import com.sedate.qrku.core.model.BarcodeHistory
import kotlin.time.Clock

class BarcodeRepositoryImpl(
	private val dao: BarcodeHistoryDao
) : BarcodeRepository {

	override suspend fun saveScan(
		result: String,
		contentType: String,
		format: String,
		formatCode: Int?
	) {
		dao.insert(
			BarcodeHistoryEntity(
				actionType = BarcodeType.Action.SCAN,
				result = result,
				contentType = contentType,
				format = format,
				formatCode = formatCode,
				createdAt = Clock.System.now()
					.toEpochMilliseconds()
			)
		)
	}

	override suspend fun saveCreate(
		content: String,
		contentType: String,
		format: String,
		formatCode: Int?
	) {
		dao.insert(
			BarcodeHistoryEntity(
				actionType = BarcodeType.Action.CREATE,
				result = content,
				contentType = contentType,
				format = format,
				formatCode = formatCode,
				createdAt = Clock.System.now()
					.toEpochMilliseconds()
			)
		)
	}

	override suspend fun getHistory(): List<BarcodeHistory> =
		dao.getAll()
			.map { it.toModel() }

	override suspend fun deleteByIds(ids: List<Long>) {
		dao.deleteByIds(ids)
	}
}