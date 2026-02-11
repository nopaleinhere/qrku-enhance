package com.sedate.qrku.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedate.qrku.core.database.converter.BarcodeActionTypeConverter
import com.sedate.qrku.core.database.dao.BarcodeHistoryDao
import com.sedate.qrku.core.database.entity.BarcodeHistoryEntity

@Database(
	entities = [BarcodeHistoryEntity::class],
	version = 1,
	exportSchema = false
)

@TypeConverters(
	BarcodeActionTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
	abstract fun barcodeHistoryDao(): BarcodeHistoryDao
}