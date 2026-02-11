package com.sedate.qrku.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object SeTextStyle {
	/* ============================================================
	 * HEADINGS
	 * ============================================================ */

	val H1 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 32.sp,
		lineHeight = 40.sp
	)

	val H2 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 28.sp,
		lineHeight = 36.sp
	)

	val H3 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.SemiBold,
		fontSize = 24.sp,
		lineHeight = 32.sp
	)

	val H4 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.SemiBold,
		fontSize = 20.sp,
		lineHeight = 28.sp
	)

	val H5 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 18.sp,
		lineHeight = 26.sp
	)

	val H6 = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 16.sp,
		lineHeight = 24.sp
	)

	/* ============================================================
	 * BODY
	 * ============================================================ */

	val BodyLarge = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp
	)

	val BodyMedium = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 14.sp,
		lineHeight = 20.sp
	)

	val BodySmall = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 12.sp,
		lineHeight = 18.sp
	)

	/* ============================================================
	 * LABEL / CAPTION
	 * ============================================================ */

	val LabelLarge = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp,
		lineHeight = 18.sp
	)

	val LabelMedium = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 12.sp,
		lineHeight = 16.sp
	)

	val LabelSmall = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 10.sp,
		lineHeight = 14.sp
	)

	/* ============================================================
	 * BUTTON / ACTION
	 * ============================================================ */

	val ButtonLarge = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.SemiBold,
		fontSize = 16.sp,
		lineHeight = 20.sp
	)

	val ButtonMedium = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.SemiBold,
		fontSize = 14.sp,
		lineHeight = 18.sp
	)

	val ButtonSmall = TextStyle(
		fontFamily = SeFontFamily,
		fontWeight = FontWeight.Medium,
		fontSize = 12.sp,
		lineHeight = 16.sp
	)

	/* ============================================================
	 * HELPER
	 * ============================================================ */

	fun disabled(style: TextStyle): TextStyle =
		style.copy(
			color = style.color.copy(alpha = 0.4f)
		)
}

internal val SeTypography = Typography(
	headlineLarge = SeTextStyle.H1,
	headlineMedium = SeTextStyle.H2,
	headlineSmall = SeTextStyle.H3,

	titleLarge = SeTextStyle.H4,
	titleMedium = SeTextStyle.H5,
	titleSmall = SeTextStyle.H6,

	bodyLarge = SeTextStyle.BodyLarge,
	bodyMedium = SeTextStyle.BodyMedium,
	bodySmall = SeTextStyle.BodySmall,

	labelLarge = SeTextStyle.LabelLarge,
	labelMedium = SeTextStyle.LabelMedium,
	labelSmall = SeTextStyle.LabelSmall
)

private val SeFontFamily = FontFamily.Default
