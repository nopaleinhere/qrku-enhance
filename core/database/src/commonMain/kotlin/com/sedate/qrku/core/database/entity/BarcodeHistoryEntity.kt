package com.sedate.qrku.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.ZERO

@Entity(tableName = "barcode_history")
data class BarcodeHistoryEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Long = Long.ZERO,
	val actionType: BarcodeType.Action,
	val result: String,
	val contentType: String,
	val format: String,
	val formatCode: Int?,
	val createdAt: Long
)