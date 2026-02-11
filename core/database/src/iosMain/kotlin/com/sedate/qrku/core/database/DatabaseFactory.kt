package com.sedate.qrku.core.database

import androidx.room.Room
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
	actual fun create(): AppDatabase {
		val urls = NSFileManager.defaultManager.URLsForDirectory(
			directory = NSDocumentDirectory,
			inDomains = NSUserDomainMask
		)

		val documentsDir =
			urls.firstNotNullOfOrNull { it as? NSURL } ?: error("Documents directory not found")

		val dbPath = documentsDir.URLByAppendingPathComponent("barcode.db")?.path
			?: error("Invalid database path")

		return Room.databaseBuilder<AppDatabase>(
			name = dbPath
		)
			.build()
	}
}