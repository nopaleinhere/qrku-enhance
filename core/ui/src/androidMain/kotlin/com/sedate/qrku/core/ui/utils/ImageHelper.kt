package com.sedate.qrku.core.ui.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
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
import androidx.core.graphics.createBitmap
import androidx.core.graphics.toColorInt

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
	val watermarkedBitmap = bitmap.withQrkuWatermark()
	
	val cachePath = File(
		context.cacheDir,
		"images"
	)
	cachePath.mkdirs()
	
	val file = File(
		cachePath,
		"qrku.png"
	)
	
	FileOutputStream(file).use {
		watermarkedBitmap.compress(
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
		putExtra(Intent.EXTRA_STREAM, uri)
		addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
	}
	
	context.startActivity(
		Intent.createChooser(
			intent,
			"Share QRKU"
		)
	)
}

fun Bitmap.withQrkuWatermark(): Bitmap {
	val extraBottomSpace = 76
	
	val result = createBitmap(width, height + extraBottomSpace)
	
	val canvas = Canvas(result)
	
	canvas.drawColor(Color.WHITE)
	
	canvas.drawBitmap(
		this,
		0f,
		0f,
		null
	)
	
	val qrkuPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		color = "#6EC1FF".toColorInt()
		textSize = 52f
		typeface = Typeface.create(
			Typeface.DEFAULT,
			Typeface.BOLD
		)
	}
	
	val sedatePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		color = Color.BLACK
		textSize = 18f
		typeface = Typeface.create(
			Typeface.DEFAULT,
			Typeface.NORMAL
		)
	}
	
	val qrkuText = "QRKU"
	val sedateText = "with Sedate"
	
	val qrkuWidth = qrkuPaint.measureText(qrkuText)
	val sedateWidth = sedatePaint.measureText(sedateText)
	
	val totalWidth = maxOf(
		qrkuWidth,
		sedateWidth
	)
	
	val qrkuX = width - totalWidth - 32f
	val sedateX = width - totalWidth - 16f
	
	val qrkuY = height + 34f
	val sedateY = qrkuY + 12f
	
	canvas.drawText(
		qrkuText,
		qrkuX,
		qrkuY,
		qrkuPaint
	)
	
	canvas.drawText(
		sedateText,
		sedateX,
		sedateY,
		sedatePaint
	)
	
	return result
}