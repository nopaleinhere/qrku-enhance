package com.sedate.qrku.core.ui.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.sedate.qrku.core.common.constants.SeConst.ONE_HUNDRED
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

fun saveBitmapToGallery(
	context: Context,
	bitmap: Bitmap
): Uri? {
	val resolver = context.contentResolver

	val filename = "qr_${System.currentTimeMillis()}.png"
	val mimeType = "image/jpeg"

	val values = ContentValues().apply {
		put(
			MediaStore.MediaColumns.DISPLAY_NAME,
			filename
		)
		put(
			MediaStore.MediaColumns.MIME_TYPE,
			mimeType
		)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			put(
				MediaStore.MediaColumns.RELATIVE_PATH,
				Environment.DIRECTORY_PICTURES
			)
		}
	}

	var uri: Uri? = null
	try {
		uri = resolver.insert(
			MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
			values
		)
		uri?.let {
			val outputStream: OutputStream? = resolver.openOutputStream(it)
			outputStream?.use { stream ->
				bitmap.compress(
					Bitmap.CompressFormat.JPEG,
					100,
					stream
				)
			}
		} ?: throw IOException("Failed to create new MediaStore record.")
		return uri
	} catch (e: IOException) {
		e.printStackTrace()
		if (uri != null) {
			resolver.delete(
				uri,
				null,
				null
			)
		}
		return null
	}
}

fun shareBitmap(
	context: Context,
	bitmap: Bitmap
) {
	val cachePath = File(
		context.cacheDir,
		"images"
	)
	cachePath.mkdirs()

	val file = File(
		cachePath,
		"qr.png"
	)

	FileOutputStream(file).use {
		bitmap.compress(
			Bitmap.CompressFormat.PNG,
			Int.ONE_HUNDRED,
			it
		)
	}

	val uri: Uri = FileProvider.getUriForFile(
		context,
		"${context.packageName}.provider",
		file
	)

	val intent = Intent(Intent.ACTION_SEND).apply {
		type = "image/png"
		putExtra(
			Intent.EXTRA_STREAM,
			uri
		)
		addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
	}

	context.startActivity(
		Intent.createChooser(
			intent,
			"Share QR"
		)
	)
}
