package com.sedate.qrku.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(): DataStore<Preferences>

internal const val DATA_STORE_FILE_NAME = "qrku.preferences_pb"