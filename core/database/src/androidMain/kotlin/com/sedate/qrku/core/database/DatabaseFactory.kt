package com.sedate.qrku.core.database

import android.content.Context
import androidx.room.Room

actual class DatabaseFactory(
	private val context: Context
) {
	actual fun create(): AppDatabase =
		Room.databaseBuilder(
			context,
			AppDatabase::class.java,
			"barcode.db"
		).build()
}