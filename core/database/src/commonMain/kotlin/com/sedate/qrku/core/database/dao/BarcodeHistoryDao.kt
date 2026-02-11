package com.sedate.qrku.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedate.qrku.core.database.entity.BarcodeHistoryEntity

@Dao
interface BarcodeHistoryDao {
	@Query("SELECT * FROM barcode_history ORDER BY createdAt DESC")
	suspend fun getAll(): List<BarcodeHistoryEntity>

	@Insert
	suspend fun insert(entity: BarcodeHistoryEntity)

	@Query("DELETE FROM barcode_history WHERE id IN (:ids)")
	suspend fun deleteByIds(ids: List<Long>)

	@Query("DELETE FROM barcode_history")
	suspend fun clear()
}