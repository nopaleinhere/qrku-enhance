package com.sedate.qrku.core.database

expect class DatabaseFactory {
	fun create(): AppDatabase
}